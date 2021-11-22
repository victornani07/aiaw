import jade.core.Agent;

import java.io.File;
import java.util.Date;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

// Am implementat functionalitatea clasei FileAgent in terminal
// N-am facut un framework precum cel de la exemplul din laborator
// Principiul este foarte simplu
// Initial, introducem denumirea fisierului in terminal, care este creat in directorul ce contine clasele
// Iar apoi introducem denumirea operatiei
// m impune agentul sa verifice daca fisierul a fost modificat
// r impune agentul sa verifice daca fisierul a fost sters
// Astfel, simulam activitatea agentului FileAgent

// Am incercat sa vin cu propria implementare pentru aceste doua operatii, evitand sa folosesc
// implementarea de la laborator
// Pentru a vedea daca fisierul a fost modificat, accesam metoda fileLastModified() tipice fisierelor
// care trateaza data la care a fost modificat si o compara cu data trecuta
// Daca coincid, inseamna ca nu a fost modificat, iar daca nu - da

// Stergerea se bazeaza pe cautarea fisierului in director
// Initial, verifica daca exista
// Daca fisierul nu exista initial, agentul este oprit, afisand un mesaj corespunzator
// Daca exista, acesta asteapta un mesaj pentru a-si continua activitatea
// Cat timp el asteapta, noi putem sterge fisierul sau putem evita aceasta operatie
// Deci practic agentul verifica daca fisierul a existat inainte de operatie si verifica daca exita ulterior

public class FileAgent extends Agent {
    protected void setup() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the file name with the extension(\".txt\" in our case): ");
        String path = scanner.next();

        System.out.println("Enter the operation you want the agent to perform.");
        System.out.println("Enter 'm' to check if the file has been modified.");
        System.out.println("Enter 'r' to check if the files has been deleted.");
        System.out.print("Enter: ");
        char c = scanner.next().charAt(0);

        if(c == 'm') {
            if(new File(path).exists()) {
                TimerTask task = new FileWatcher(new File(path));
                Timer timer = new Timer();
                timer.schedule(task, new Date(), 1000);
            } else {
                System.out.println("File does not exist.");
                System.exit(-1);
            }
        } else if(c == 'r') {
            if(new File(path).exists()) {
                // Enter yes or no
                System.out.println("Shall I continue? ");
                String wait = scanner.next();
                if(wait.equals("no"))
                    System.exit(0);
            } else {
                System.out.println("The file does not exist.");
                System.exit(-1);
            }
            File file = new File(path);
            if(file.exists())
                System.out.println("File has not been removed.");
            else
                System.out.println("File has been removed.");
        }
    }

}
