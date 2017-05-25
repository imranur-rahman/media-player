package media_player_project;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.io.File;
import java.util.List;

/**
 * Created by Shimul on 12/8/2015.
 */
public class PlayListController {
    /*@FXML
    private Button add;

    @FXML
    private Button delete;*/

    @FXML
    private ListView<String> playList = new ListView();

    private ObservableList<String> playListFiles = FXCollections.observableArrayList();
    private ObjectProperty<File> selectedMedia = new SimpleObjectProperty<>();
    private ObjectProperty<File> deletedMedia = new SimpleObjectProperty<>();

    //@Override
    //public void initialize(URL location, ResourceBundle resources) {
        /*playList.setOnMouseClicked((click) -> {
            if (click.getClickCount() == 2) {
                if (playList.getSelectionModel().getSelectedItem() != null) {
                    selectedMedia.setValue((File) playList.getSelectionModel().getSelectedItem());
                }
            }
        });*/
    //}

    /*@FXML
    void add(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Files",
                        PropertiesUtils.readFormats()));
        List<Path> listOfFiles = new ArrayList<Path>();
        listOfFiles = FileUtils.convertListFiletoListPath(chooser.showOpenMultipleDialog(((Button) event.getSource()).getScene().getWindow()));
        if (listOfFiles != null) {
            listOfFiles.stream().forEach(System.out::println);
            listOfFiles.stream().forEach(playListFiles::add);
            playListFiles.stream().forEach(System.out::println);
            playList.setItems(playListFiles);
        }
    }*/

    /*@FXML
    void delete(ActionEvent event) {
        try {
            if (playList.getSelectionModel().getSelectedItem() != null) {
                if (null != playListFiles || !playListFiles.isEmpty()) {
                    deletedMedia.setValue((File) playList.getSelectionModel().getSelectedItem());
                    playListFiles.remove(playList.getSelectionModel().getSelectedItem());
                    playList.setItems(playListFiles);
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }*/

    public void setPlayList(List list){
        //System.out.println(list);
        try {
            boolean b = playListFiles.setAll(list.toString());
            playList = new ListView<String>(playListFiles);
            //playList.setItems(playListFiles);

            //playList.setUserData(playListFiles);

            //System.out.println(playListFiles);
            //System.out.println(playList);
            int i;
            for(i = 0; i < playListFiles.size(); ++i)
                System.out.println(playListFiles.get(i));
            //for(i = 0; i < playListFiles.size(); ++i)
            System.out.println(playList.getItems());
            //playListFiles.stream().forEach(System.out::println);
        }catch(NullPointerException e){
            e.printStackTrace();
        }
    }

    public ObservableList listViewItems(){
        return playListFiles;
    }

    public ObjectProperty<File> selectedFile(){
        return selectedMedia;
    }

    public ObjectProperty<File> deletedFile() {
        return deletedMedia;
    }

}
