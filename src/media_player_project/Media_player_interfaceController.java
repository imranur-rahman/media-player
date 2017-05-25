
package media_player_project;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Path;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Media_player_interfaceController implements Initializable,Runnable {

    @FXML
    private MenuBar menuBar;

    @FXML
    public MediaView mediaView;
    
    MediaPlayer mediaplayer;
    Stage stage;
    private Media musicfile;
    private boolean playingStat=true;
    @FXML
    public Button play;
    @FXML
    public Slider timeSlider;
    @FXML
    public Slider volumeSlider;
    @FXML
    private MenuItem open_menu;
    @FXML
    private MenuItem playlist_menu;

    @FXML
    private  Button stopButton;
    @FXML
    private  Button forwardButton;
    @FXML
    private Button backwordButton;
    @FXML
    private Button volumeButton;

    private Image play_image;
    private Image pause_image;
    private Image stop_image;
    private Image right_image;
    private Image left_image;
    private Image vol;
    private Image mute;
    private boolean single=false;
    private boolean list=false;
    private boolean sound=true;
    private boolean mediastat=false;
    private boolean ful=false;
    private boolean controlvis=true;

    private Scene playlistScene;
    private PlayListController playlistController;

    private ObservableList<String> playListFiles = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        play_image=new Image("file:/D:/aim/media_player_project(1)/icons/play.png");
        pause_image=new Image("file:/D:/aim/media_player_project(1)/icons/pause.png");
        stop_image=new Image("file:/D:/aim/media_player_project(1)/icons/stop.png");
        right_image=new Image("file:/D:/aim/media_player_project(1)/icons/right.png");
        left_image=new Image("file:/D:/aim/media_player_project(1)/icons/left.png");
        vol=new Image("file:/D:/aim/media_player_project(1)/icons/volume.png");
        mute=new Image("file:/D:/aim/media_player_project(1)/icons/mute.png");

        play.setGraphic(new ImageView(play_image));
        stopButton.setGraphic(new ImageView(stop_image));
        forwardButton.setGraphic(new ImageView(right_image));
        backwordButton.setGraphic(new ImageView(left_image));
        volumeButton.setGraphic(new ImageView(vol));
        Media_player_interfaceController_intial(null);
    }
    public  void Media_player_interfaceController_intial(String uri){
              if(uri!=null)
              {
              this.musicfile = new Media(uri);
              if(mediaplayer!=null){
                  mediaplayer.stop();
              }
              mediaplayer=new MediaPlayer(musicfile);

              mediaplayer.setOnReady(new Runnable() {
                  @Override
                  public void run() {
                      timeSlider.setMin(0.0);
                      timeSlider.setValue(0.0);
                      timeSlider.setMax(mediaplayer.getTotalDuration().toSeconds());


                  }
              });
              volumeSlider.setValue(100);
                
              mediaplayer.setAutoPlay(true);
                  mediastat=true;
                  //play.setGraphic(new ImageView(pause_image));

              mediaView.setMediaPlayer(mediaplayer);

                 mediaplayer .currentTimeProperty().addListener((observableValue, duration, current) -> {
                     timeSlider.setValue(current.toSeconds());
                 });

                  timeSlider.setOnMouseClicked(new EventHandler<MouseEvent>() {
                      @Override
                      public void handle(MouseEvent event) {
                          mediaplayer.seek(javafx.util.Duration.seconds((timeSlider.getValue())));

                      }
                  });

                  mediaView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                                  @Override
                                                  public void handle(MouseEvent event) {
                                                      if(event.getClickCount()==2){
                                                          if(ful==false) {
                                                              stage.setFullScreen(true);
                                                              ful = true;
                                                          }
                                                      }
                                                      else {
                                                          stage.setFullScreen(false);
                                                          ful = false;
                                                      }
                                                  }
                                              }
                  );

                  volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
                      @Override
                      public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                          mediaplayer.setVolume((newValue.doubleValue()) / 100);

                      }

                  });


            
            }
        }

    public DoubleProperty timesliderwidth()
    {
        return timeSlider.prefWidthProperty();


    }
    public DoubleProperty mediaviewwidth()
    {
        return mediaView.fitWidthProperty();
    }
    public DoubleProperty mediaviewheight()
    {
        return mediaView.fitHeightProperty();
    }

    public void run(){
        
    }
   @FXML
    private void playButtonAction(ActionEvent event) {
       if (mediastat == true)
       {
           mediaplayer.pause();
           play.setGraphic(new ImageView(play_image));
           mediastat=false;
       }
       else
       {
           mediastat=true;
           mediaplayer.play();
           play.setGraphic(new ImageView(pause_image));
       }

    }



    @FXML
    private void open_menu_action(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        configureFileChooser(fileChooser);
        File file=fileChooser.showOpenDialog(null);
        if(file!=null){
           Media_player_interfaceController_intial(file.toURI().toString());
        }
     }
    @FXML
    private void  playlist_menu_action(ActionEvent event)
    {
        FileChooser fileChooser=new FileChooser();
        configureFileChooser(fileChooser);

        List<File>list = new ArrayList<File>();

        list = fileChooser.showOpenMultipleDialog(null);
        if(list!=null)
        {
            PlayListController playListController = new PlayListController();
            //System.out.println(list);
            try {
                playListController.setPlayList(list);
            }catch(NullPointerException e){
                e.printStackTrace();
            }

            for(File file:list){
                if(mediaplayer==null){
                    Media_player_interfaceController_intial(file.toURI().toString());
                }

                else
                {
                   mediaplayer.setOnEndOfMedia(new Runnable() {
                       @Override
                       public void run() {
                           mediaplayer.stop();
                           Media_player_interfaceController_intial(file.toURI().toString());
                       }
                   });

                }
            }
        }
    }

    @FXML
    void openPlaylist(ActionEvent event) {
        Stage stage = new Stage();
        stage.setScene(playlistScene);
        //stage.initOwner(((Button) event.getSource()).getScene().getWindow());
        stage.show();
        Bindings.bindContentBidirectional(playListFiles, playlistController.listViewItems());
        //selectedMedia.bind(playlistController.selectedFile());
        //deletedMedia.bind(playlistController.deletedFile());

    }

    public void injectPlayListController(PlayListController playlistController) {

        this.playlistController = playlistController;
    }

    public void injectPlayListRoot(Parent playlistRoot) {
        playlistScene = new Scene(playlistRoot);
    }

    private void configureFileChooser(FileChooser fileChooser)
    {
                fileChooser.setTitle("Media Files");
                fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home")));
                fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Media Files", "*.*"),
                new FileChooser.ExtensionFilter("mp3","*.mp3"),
               new FileChooser.ExtensionFilter( "mp4","*.mp4")
               );
     }

    public void setStage(Stage stage){
        this.stage=stage;
    }

    
   

    
    
    
    
    
    
    
    
    
    
    
    
}
