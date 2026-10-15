public class Book {
    private int id;
    private String title;
    private String author;
    private boolean issued;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isIssued() {
        return issued;
    }

    public void setIssued(boolean issued) {
        this.issued = issued;
    }

    public Book(int id,String title,String author) {
        this.id = id;
        this.title=title;
        this.author=author;
        this.issued=false;

    }
}
