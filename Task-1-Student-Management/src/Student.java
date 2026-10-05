public class Student{
   private int id;
   private  String name;
   private int marks;
   Student(int id,String name,int marks)
   {
       this.id=id;
       this.name=name;
       this.marks=marks;
   }
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setMarks(int marks) {
        this.marks = marks;
    }
    public int getMarks() {
        return marks;
    }

}