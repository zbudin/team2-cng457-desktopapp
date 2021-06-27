package desktopapp.Controllers;

import desktopapp.Models.Comment;

public class DetailsThread implements Runnable{
    Thread thread;
    private String name;
    private long id;
    private boolean isPc;
    public String text;
    public boolean isDone;

    DetailsThread(String name,long id,boolean isPc,String details){
        this.name = name;
        this.id = id;
        this.isPc = isPc;
        text = details;
        isDone = false;
    }

    @Override
    public void run(){
        try{
            String strings[] = Controller.getFeatures(id, isPc);
            int count = 0;
            for (String s : strings) {
                count++;
                text += "\nAdditional Feature " + count + ": " + s;
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
