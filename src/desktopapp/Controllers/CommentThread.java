package desktopapp.Controllers;

import desktopapp.Models.Comment;
import desktopapp.Models.PC;
import desktopapp.Models.Phone;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.Text;

public class CommentThread implements Runnable {
    Thread thread;
    private String name;
    private long id;
    private boolean isPc;
    public String text;
    public boolean isDone;

    CommentThread(String name,long id,boolean isPc){
        this.name = name;
        this.id = id;
        this.isPc = isPc;
        text = "";
        isDone = false;
    }
    @Override
    public void run(){
        try{
            Comment comments[] = Controller.getComments(id, isPc);
            if (comments.length > 0) {
                text += "\n\nComments\n";
                int count = 0;
                for (Comment c : comments) {
                    count++;
                    text += "\n\nComment " + count + "\n" + c;
                    if(count==3){
                        break;
                    }
                }
            } else {
                text += "\n\nNo one commented to this product yet!";
            }
        }
        catch(Exception e){System.out.println(e);}
        isDone = true;
    }
    public void join() throws InterruptedException {
        thread.join();
    }
    public void start(){
        if (thread==null){
            thread = new Thread(this, name);
            thread.start();
        }
    }
}
