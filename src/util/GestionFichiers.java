package util;
import java.io.*;


public class GestionFichiers {

  private BufferedReader bR;
  private BufferedWriter bf;
  private FileWriter fW;
  private File f;
  public static String newline = System.getProperty("line.separator");

  public GestionFichiers(File f) throws IOException {
    this.f = f;
  }

  public void ouvrir() {
    try {
      this.bR = new BufferedReader(new FileReader(this.f));

    } catch (Exception e) {
      System.out.println("Erreur : "+e.getMessage());
    }
  }

  public void ecrire(String txt) throws IOException {
    this.fW = new FileWriter(this.f, true);
    if (txt != null) {
      this.fW.write(txt+"\r\n");
    }
    this.fW.close();
  }

  public void remplacer(String txt) throws IOException {
    this.bf = new BufferedWriter(new FileWriter(this.f));
    if (txt != null) {
      this.bf.write(txt);
    }
    this.bf.close();
  }

  public String lire() throws IOException {
    return this.bR.readLine();
  }

  public void fermer() {
    try {
      this.bR.close();
    } catch (Exception e) {
      System.out.println("Erreur : "+e.getMessage());
    }
  }

  public BufferedReader getBR() {
    return this.bR;
  }

  public File getFile() {
    return this.f;
  }
}
