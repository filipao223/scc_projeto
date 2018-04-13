// Classe para gerao de nmeros aleatrios segundos vrias distribuies
// Apenas a distribuio exponencial negativa est definida

public class Aleatorio {

    private double vals_x2[] = new double[8];
    // Gera um numero segundo uma distribuio exponencial negativa de media m
    static double exponencial (double m){
		return (-m*Math.log(Math.random()));
	}

	protected double normal (int stream, double m, int dp){
        double u1, u2;
        double v1=0, v2=0, w=2;
        double y1, y2;
        double x1, x2;

        u1 = RandomGenerator.rand(stream);
        u2 = RandomGenerator.rand(stream);

        while(w > 1){
            v1 = 2*u1 - 1;
            v2 = 2*u2 - 1;
            w = Math.pow(v1, 2);
        }

        y1 = v1 * Math.pow((-2*Math.log1p(w))/w, 0.5);
        y2 = v2 * Math.pow((-2*Math.log1p(w))/w, 0.5);

        x1 = m + y1 * dp;
        x2 = m + y2 * dp;

        if(x1 < 0) x1 = 0;
        if(x2 < 0) x2 = 0;

        //Procura x2 já existente
        for(int i=0; i<8; i+=2){
            if(this.vals_x2[i] == stream && this.vals_x2[i+1] >= 0){
                //Devolve o x2
                double temp = x2;
                this.vals_x2[i+1] = -1;
                return temp;
            }
        }

        //Guarda x2
        for(int i=0; i<8; i+=2){
            if(this.vals_x2[i] < 0){
                this.vals_x2[i] = stream;
                this.vals_x2[i+1] = x2;
            }
        }

        return x1;
    }

}