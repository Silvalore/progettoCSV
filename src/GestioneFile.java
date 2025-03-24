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

        // Legge il file CSV e popola la lista dei record
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

        // Salva i dati su file
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

        // Aggiunge un nuovo record
        public void aggiungiRecord(String[] campi) {
            records.add(new Record(campi));
            salvaCSV();
        }

        // Cerca un record per un campo specifico
        public Record cercaRecord(int colonna, String valore) {
            for (Record r : records) {
                if (r.getCampi()[colonna].equals(valore)) {
                    return r;
                }
            }
            return null;
        }

        // Modifica un record esistente
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

        // Cancella logicamente un record
        public void cancellaRecord(int colonna, String valore) {
            Record record = cercaRecord(colonna, valore);
            if (record != null) {
                record.setCancellato(true);
                salvaCSV();
            } else {
                System.out.println("Record non trovato.");
            }
        }

        // Mostra i primi 3 campi di tutti i record
        public void visualizzaDati() {
            for (Record r : records) {
                String[] campi = r.getCampi();
                System.out.println(" | " + String.join(" | ", Arrays.copyOf(campi, Math.min(3, campi.length))));
            }
        }

    }

