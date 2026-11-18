class cube {

    public static double A, B, C;   // Rotation angles around each axis

    public static double cubeWidth = 10;
    public static int width = 160;
    public static int height = 44;
    // Screen size in characters

    public static double[] zbuffer = new double[width * height];
    public static char[] buffer = new char[width * height];
    public static int backgroundASCIICode = ' ';
    public static double K1 = 40;

    public static double distanceFromCam = 60;
    public static double incrementspeed = 0.6;

    public static double x, y, z;
    public static double ooz;
    public static int xp, yp;
    public static int idx;

    public static double CalculateX(double i, double j, double k) { // Rotated x coordinate
        return j * Math.sin(A) * Math.sin(B) * Math.cos(C) - k * Math.cos(A) * Math.sin(B) * Math.cos(C)
        + j * Math.cos(A) * Math.sin(C) + k * Math.sin(A) * Math.sin(C) + i * Math.cos(B) * Math.cos(C);
    } // CalculateX end.

    public static double CalculateY(double i, double j, double k) { // Rotated y coordinate
        return j * Math.cos(A) * Math.cos(C) + k * Math.sin(A) * Math.cos(C) - j * Math.sin(A) * Math.sin(B) * Math.sin(C)
        + k * Math.cos(A) * Math.sin(B) * Math.sin(C) - i * Math.cos(B) * Math.sin(C);
    } // CalculateY end.

    public static double CalculateZ(double i, double j, double k) { // Rotated z coordinate
        return k * Math.cos(A) * Math.cos(B) - j * Math.sin(A) * Math.cos(B) + i * Math.sin(B);
    } // CalculateZ end.

    public static void calculateForSurface(double cubeX, double cubeY, double cubeZ, int ch) {
        x = CalculateX(cubeX, cubeY, cubeZ);
        y = CalculateY(cubeX, cubeY, cubeZ);
        z = CalculateZ(cubeX, cubeY, cubeZ) + distanceFromCam;

        ooz = 1 / z; // "one over z": larger value = closer to the camera

        xp = (int) (width / 2 + K1 * ooz * x * 2); // x is doubled because characters are taller than wide
        yp = (int) (height / 2 + K1 * ooz * y);

        idx = xp + yp * width;
        if (xp >= 0 && xp < width && yp >= 0 && yp < height) {
            if (ooz > zbuffer[idx]) { // z-buffer test: keep only the closest point
                zbuffer[idx] = ooz;
                buffer[idx] = (char) ch;
            }
        }
    } // calculateForSurface end.

    public static void main(String[] args) throws InterruptedException {
        System.out.print("\u001b[2J"); // Clear the terminal once

        while (true) {
            java.util.Arrays.fill(buffer, (char) backgroundASCIICode);
            java.util.Arrays.fill(zbuffer, 0);

            // Draw the six faces of the cube
            for (double cubeX = -cubeWidth; cubeX < cubeWidth; cubeX += incrementspeed) {
                for (double cubeY = -cubeWidth; cubeY < cubeWidth; cubeY += incrementspeed) {
                    calculateForSurface(cubeX, cubeY, -cubeWidth, '.');
                    calculateForSurface(cubeWidth, cubeY, cubeX, '$');
                    calculateForSurface(-cubeWidth, cubeY, -cubeX, '~');
                    calculateForSurface(-cubeX, cubeY, cubeWidth, '#');
                    calculateForSurface(cubeX, -cubeWidth, -cubeY, ';');
                    calculateForSurface(cubeX, cubeWidth, cubeY, '+');
                }
            }

            // Print the whole frame at once to reduce flicker
            StringBuilder frame = new StringBuilder("\u001b[H"); // Move cursor to top-left
            for (int k = 0; k < width * height; k++) {
                frame.append(k % width != 0 ? buffer[k] : '\n');
            }
            System.out.print(frame);
            System.out.flush();

            A += 0.05;
            B += 0.05;
            C += 0.01;
            Thread.sleep(16);
        }
    } // main end.
} // cube.java end.
