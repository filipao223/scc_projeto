package scc;

// Classe para geraçao de numeros aleatorios segundos varias distribuiçoes
// Apenas a distribuiçao exponencial negativa esta definida
public class Aleatorio {

    private static double vals_x2[] = new double[12];
    // Gera um numero segundo uma distribuio exponencial negativa de media m
    static double exponencial (double m){
		return (-m*Math.log(Math.random()));
	}

	static double normal (int stream, double m, double dp){
        double u1, u2;
        double v1=0, v2=0, w=2;
        double y1, y2;
        double x1, x2;

        while(w > 1){
            u1 = RandomGenerator.rand(stream);
            u2 = RandomGenerator.rand(stream);
            v1 = 2*u1 - 1;
            v2 = 2*u2 - 1;
            w = Math.pow(v1*v2, 2);
        }

        y1 = v1 * Math.pow((-2*Math.log(w))/w, 0.5);
        y2 = v2 * Math.pow((-2*Math.log(w))/w, 0.5);

        x1 = m + y1 * dp;
        x2 = m + y2 * dp;

        if(x1 < 0) x1 = 0;
        if(x2 < 0) x2 = 0;

        //Procura x2 já existente
        for(int i=0; i<12; i+=2){
            if(vals_x2[i] == stream && vals_x2[i+1] >= 0){
                //Devolve o x2
                double temp = x2;
                vals_x2[i+1] = -1;
                return temp;
            }
        }

        //Guarda x2
        for(int i=0; i<12; i+=2){
            if(vals_x2[i] < 0){
                vals_x2[i] = stream;
                vals_x2[i+1] = x2;
            }
        }

        return x1;
    }

}