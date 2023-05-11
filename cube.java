class cube { 

    public static double A, B, C;   // Angle

    public static double CalculateX(int i, int j, int k){   // Calculating x-axis
        return j * Math.sin(A) * Math.sin(B) * Math.cos(C) - k * Math.cos(A) * Math.sin(B) * Math.cos(C) 
        + j * Math.sin(A) * Math.sin(C) + k * Math.sin(A) * Math.sin(C) + i * Math.cos(B) * Math.cos(C);
    } // CalculateX end.

    public static double CalculateY(int i, int j, int k){   // Calculating y-axis
        return j * Math.cos(A) * Math.cos(C) + k * Math.sin(A) * Math.cos(C) - j * Math.sin(A) * Math.sin(B) * Math.sin(C) 
        + k * Math.cos(A) * Math.sin(B) * Math.sin(C) - i * Math.cos(B) * Math.sin(C);
    } // CalculateY end.

    public static double CalculateZ(int i, int j, int k){   // Calculating z-axis
        return k * Math.cos(A) * Math.cos(B) - j * Math.sin(A) *Math.cos(B) + i * Math.sin(B);
    } // CalculateZ end.
}