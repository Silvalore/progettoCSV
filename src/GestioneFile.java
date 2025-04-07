import java.io.*;
import java.util.*;

    public class GestioneFile {
        private String filePath;
        private List<Record> records = new ArrayList<>();
        private String[] intestazione;

        public GestioneFile(String filePath) {
            this.filePath = filePath;
            leggiCSV();
        }


        public void leggiCSV() {
            try (BufferedReader br = new BufferedReader(new FileReader("src/silva.csv"))) {
                String linea;
                boolean primaRiga = true;

                while ((linea = br.readLine()) != null) {
                    String[] campi = linea.split(",");
                    if (primaRiga) {
                        intestazione = Arrays.copyOf(campi, campi.length + 2);
                        intestazione[intestazione.length - 2] = "miovalore";
                        intestazione[intestazione.length - 1] = "cancellato";
                        primaRiga = false;
                    } else {
                        records.add(new Record(campi));
                    }
                }
            } catch (IOException e) {
                System.out.println("Errore nella lettura del file: " + e.getMessage());
            }
        }


        public void salvaCSV() {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/silva.csv"))) {
                bw.write(String.join(",", intestazione) + "\n");
                for (Record record : records) {
                    bw.write(record.toString() + "\n");
                }
                System.out.println("Dati salvati correttamente.");
            } catch (IOException e) {
                System.out.println("Errore nella scrittura del file: " + e.getMessage());
            }
        }


        public void aggiungiRecord(String[] campi) {
            records.add(new Record(campi));
            salvaCSV();
        }


        public Record cercaRecord(int colonna, String valore) {
            for (Record r : records) {
                if (r.getCampi()[colonna].equals(valore)) {
                    return r;
                }
            }
            return null;
        }


        public void modificaRecord(int colonna, String valore, String[] nuoviCampi) {
            Record record = cercaRecord(colonna, valore);
            if (record != null) {
                records.remove(record);
                records.add(new Record(nuoviCampi));
                salvaCSV();
            } else {
                System.out.println("Record non trovato.");
            }
        }


        public void cancellaRecord(int colonna, String valore) {
            Record record = cercaRecord(colonna, valore);
            if (record != null) {
                record.setCancellato(true);
                salvaCSV();
            } else {
                System.out.println("Record non trovato.");
            }
        }

