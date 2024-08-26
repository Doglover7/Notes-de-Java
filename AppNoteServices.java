
import java.util.ArrayList;

public class AppNoteServices {

  ArrayList<String> noteList;

  public AppNoteServices() {
    noteList = new ArrayList<>();
    noteList.add("Go to shopping");
    noteList.add("Buy an ice cream");
    noteList.add("Play some games");
    noteList.add("Do the homework");
    noteList.add("Do the dishes");
    noteList.add("Going to school");
    noteList.add("Going to clean the bedroom");
  }


  public ArrayList<String> onClickButtonListNotes() {
    System.out.println("AppNoteServices:onClickButtonListNotes");
    return null;
  }


  public ArrayList<String> onClickButtonSearchNotes(String keyword) {
    System.out.println("AppNoteServices:onClickButtonSearchNotes:" + keyword);
    return null;
  }


  public ArrayList<String> onClickButtonSaveNote(String note) {
    System.out.println("AppNoteServices:onClickButtonSaveNote:" + note);
    return null;
  }


  public ArrayList<String> onClickListSort(String sortMode) {
    System.out.println("AppNoteServices:onClickListSort:" + sortMode);
    return null;
  }

}

//add word and letter counter