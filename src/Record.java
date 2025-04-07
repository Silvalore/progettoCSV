import java.util.Random;

    public class Record {
        private String[] campi;
        private int mioValore;
        private boolean cancellato;

        public Record(String[] campi) {
            this.campi = campi;
            this.mioValore = new Random().nextInt(11) + 10; // Genera valore tra 10 e 20
            this.cancellato = false;
        }

        public String[] getCampi() {
            return campi;
        }

        public int getMioValore() {
            return mioValore;
        }

        public boolean isCancellato() {
            return cancellato;
        }

        public void setCancellato(boolean cancellato) {
            this.cancellato = cancellato;
        }

        @Override
        public String toString() {
            return ","+ campi + "," + mioValore + "," + cancellato + "1" + "0";
        }
    }
