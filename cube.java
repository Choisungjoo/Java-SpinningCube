import java.util.Arrays;

class cube { 

    public static double A, B, C;   // Angle
    
    public static double cubeWidth = 10; 
    public static int width = 160;
    public static int height = 44;
    // Cubes size
    
    public static double[] zbuffer = {160 * 44};
    public static char[] buffer = {160 * 44};
    public static int backgroundASCIICode = '.';
    public static double K1 = 40;

    public static double distanceFromCam = 60;
    public static double incrementspeed = 0.6;

    public static double x, y, z;
    public static double ooz;
    public static int xp, yp;
    public static int idx;
    

    public static double CalculateX(int i, int j, int k){ // Calculating x-axis
        return j * Math.sin(A) * Math.sin(B) * Math.cos(C) - k * Math.cos(A) * Math.sin(B) * Math.cos(C) 
        + j * Math.sin(A) * Math.sin(C) + k * Math.sin(A) * Math.sin(C) + i * Math.cos(B) * Math.cos(C);
    } // CalculateX end.

    public static double CalculateY(int i, int j, int k){ // Calculating y-axis
        return j * Math.cos(A) * Math.cos(C) + k * Math.sin(A) * Math.cos(C) - j * Math.sin(A) * Math.sin(B) * Math.sin(C) 
        + k * Math.cos(A) * Math.sin(B) * Math.sin(C) - i * Math.cos(B) * Math.sin(C);
    } // CalculateY end.

    public static double CalculateZ(int i, int j, int k){ // Calculating z-axis
        return k * Math.cos(A) * Math.cos(B) - j * Math.sin(A) *Math.cos(B) + i * Math.sin(B);
    } // CalculateZ end.

    public static void calculateForSurface(double cubeX, double cubeY, double cubeZ, int ch){
        y = CalculateY((int)cubeX, (int)cubeY, (int)cubeZ);
        x = CalculateX((int)cubeX, (int)cubeY, (int)cubeZ);
        z = CalculateZ((int)cubeX, (int)cubeY, (int)cubeZ) + distanceFromCam;

        ooz = 1/z;

        xp = (int)(width/2 + K1 * ooz * x * 2);
        yp = (int)(height/2 + K1 * ooz * y);

        idx = xp + yp * width;
        if(idx >= 0 && idx < width * height){
            if(ooz > zbuffer[idx]){
                zbuffer[idx] = ooz;
                buffer[idx] = (char)ch;
            }
        }

    } // calculateForSurface end.
} // cube.java end.