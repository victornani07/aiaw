import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;

// Clasa utilizata pentru a monitoriza ultima data la care a fost modificat fisierul
public class FileWatcher extends TimerTask {
    private long timeStamp;
    private final File file;

    // Initializam cu un fisier valid, fapt verificat in clasa de baza FileAgent
    public FileWatcher( File file ) {
        this.file = file;
        this.timeStamp = file.lastModified();
    }

    // Verificam data la care a fost modificat fisierul si o comparam cu data ce corespunde ultimei procesari
    // Daca acestea nu coincid, e evident ca fisierul a fost modificat
    public final void run() {
        long timeStamp = file.lastModified();

        if(this.timeStamp != timeStamp) {
            this.timeStamp = timeStamp;
            Date date = Calendar.getInstance().getTime();
            System.out.print(date);
            System.out.println(" - File has been modified.");
        }
    }
}
