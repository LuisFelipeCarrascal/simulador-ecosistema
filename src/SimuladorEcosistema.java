import java.util.Random;

public class SimuladorEcosistema {
    public static void main(String[] args) {
        char[][] ecosistema = new char[20][20];
        Random random = new Random();
        final double arbol = 0.1; // hacemos constante el valor 0,1
        final double hierba = 0.2; // hacemos constante el valor 0,2
        final int dias = 20;
        // Inicializar ecosistema
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                double prob = random.nextDouble();
                if (prob < arbol) ecosistema[i][j] = 'A'; // Árbol
                else if (prob < hierba) ecosistema[i][j] = 'H'; // Hierba
                else ecosistema[i][j] = ' '; // Vacío
            }
        }

        // Simulación por 20 días
        for (int dia = 1; dia <= dias; dia++) {
            System.out.println("Día " + dia);
            
            // Mostrar ecosistema
            for (int i = 0; i < dias; i++) {
                for (int j = 0; j < dias; j++) {
                    System.out.print(ecosistema[i][j] + " ");
                }
                System.out.println();
            }

            // Actualizar ecosistema
            char[][] nuevoEcosistema = new char[20][20];
            for (int i = 0; i < dias; i++) {
                for (int j = 0; j < dias; j++) {
                    // Contar vecinos (integrado directamente aquí)
                    int vecinos = 0;
                    for (int di = -1; di <= 1; di++) {
                        for (int dj = -1; dj <= 1; dj++) {
                            if (di == 0 && dj == 0) continue;
                            int ni = (i + di + 20) % 20;
                            int nj = (j + dj + 20) % 20;
                            if (ecosistema[ni][nj] == 'A') vecinos++;
                        }
                    }

                    if (ecosistema[i][j] == 'A') {
                        if (vecinos < 2 || vecinos > 3) nuevoEcosistema[i][j] = 'H';
                        else nuevoEcosistema[i][j] = 'A';
                    } else if (ecosistema[i][j] == 'H') {
                        if (vecinos == 3) nuevoEcosistema[i][j] = 'A';
                        else nuevoEcosistema[i][j] = 'H';
                    } else {
                        if (random.nextDouble() < 0.01) nuevoEcosistema[i][j] = 'H';
                        else nuevoEcosistema[i][j] = ' ';
                    }
                }
            }
            ecosistema = nuevoEcosistema;

            // Pausa entre días
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
