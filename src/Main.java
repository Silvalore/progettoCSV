import java.util.Scanner;
public class Main {
    public static void main(String[] args) {


                Scanner scanner = new Scanner(System.in);
                GestioneFile gestione = new GestioneFile("silva.csv");

                while (true) {
                    System.out.println("\n--- MENU ---");
                    System.out.println("1. Visualizza dati");
                    System.out.println("2. Aggiungi record");
                    System.out.println("3. Modifica record");
                    System.out.println("4. Cancella logicamente un record");
                    System.out.println("5. Conta campi");
                    System.out.println("6. Lunghezza massima campi");
                    System.out.println("7. Esci");
                    System.out.print("Scelta: ");

                    int scelta = scanner.nextInt();
                    scanner.nextLine();

                    switch (scelta) {
                        case 1:

                            break;
                        case 2:
                            System.out.print("Inserisci i campi separati da virgola: ");
                            String[] nuovoRecord = scanner.nextLine().split(",");
                            gestione.aggiungiRecord(nuovoRecord);
                            break;
                        case 3:
                            System.out.print("Campo da modificare (indice): ");
                            int campoMod = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Valore da cercare: ");
                            String valoreMod = scanner.nextLine();
                            System.out.print("Inserisci nuovi campi separati da virgola: ");
                            String[] nuoviCampi = scanner.nextLine().split(",");
                            gestione.modificaRecord(campoMod, valoreMod, nuoviCampi);
                            break;
                        case 4:
                            System.out.print("Campo da cancellare (indice): ");
                            int campoCanc = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Valore da cercare: ");
                            String valoreCanc = scanner.nextLine();
                            gestione.cancellaRecord(campoCanc, valoreCanc);
                            break;
                        case 5:

                            break;
                        case 6:

                            break;
                        case 7:
                            System.out.println("Uscita...");
                            return;
                        default:
                            System.out.println("Scelta non valida.");
                    }
                }
            }
        }
