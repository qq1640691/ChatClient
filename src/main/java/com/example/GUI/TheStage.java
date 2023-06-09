package com.example.GUI;

import com.example.ClientStatic.InfOperation;
import com.example.ClientTherad.*;
import com.example.Main;
import com.example.Tools.JavaSound.Acoustics;
import com.example.Tools.Instrument;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.sound.sampled.LineUnavailableException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import static com.example.ClientStatic.InfOperation.Sendtheinf;
import static com.example.ClientStatic.InfOperation.sendJB;
import static com.example.NettyUDP.UdpClient.UdpClientrun;
import static com.example.Tools.JavaSound.Acoustics.playfile;
import static com.example.Tools.Instrument.getCpuId;


public class TheStage {
    public static String KEY;
    public static String IP;
    public static int PORT;
    public static String ID;
    public static CopyOnWriteArrayList<String> userlist = new CopyOnWriteArrayList<>();//这个表里存储的是用户列表
    public static CopyOnWriteArrayList<String> userdelay = new CopyOnWriteArrayList<>();//已连接用户
    public static ConcurrentHashMap<String, CopyOnWriteArrayList<String>> filetopart = new ConcurrentHashMap<>();
    public static CopyOnWriteArrayList<String> titlelsit = new CopyOnWriteArrayList<>();
    public static ConcurrentHashMap<String,String> solomessage = new ConcurrentHashMap<>();
    public static CopyOnWriteArrayList<String> solomessageget = new CopyOnWriteArrayList<>();
    public static CopyOnWriteArrayList<byte[]> video = new CopyOnWriteArrayList<>();
    public static CopyOnWriteArrayList<byte[]> audio = new CopyOnWriteArrayList<>();
    public static String RSApublickey;
    public static String RSAprivatekey;
    public static Stage all = new Stage();
    public static double WIDTH = 850;
    public static double HEIGH = 430;

    public static int UDPPORT;

    public static void login() {
        Stage stage = new Stage();
        GridPane gridPane = new GridPane();
        HBox welcome = new HBox();
        HBox lg = new HBox();
        welcome.setAlignment(Pos.CENTER);
        lg.setAlignment(Pos.CENTER);
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setPadding(new Insets(-50, 0, 0, 0));
        Text title = new Text("欢迎使用");
        welcome.getChildren().add(title);
        title.setFont(Font.font(null, FontWeight.BOLD, 28));
        title.setFill(Color.rgb(0, 0, 0));
        Label aeskey = new Label("TOKEN");
        Label address = new Label("服务器IP");
        Label port = new Label("服务器端口");
        PasswordField keyfile = new PasswordField();
        TextField addressfile = new TextField();
        TextField portfile = new TextField();
        keyfile.setText("0000000000000000");
        addressfile.setText("");
        portfile.setText("41000");
        title.setTextAlignment(TextAlignment.CENTER);
        gridPane.add(aeskey, 0, 2);
        gridPane.add(keyfile, 1, 2);
        gridPane.add(address, 0, 3);
        gridPane.add(port, 0, 4);
        gridPane.add(addressfile, 1, 3);
        gridPane.add(portfile, 1, 4);
        gridPane.add(welcome, 0, 0, 11, 1);
        Button loginbutton = new Button();
        loginbutton.setText("登录");
        loginbutton.setFont(Font.font(null, FontWeight.BOLD, 15));
        lg.getChildren().add(loginbutton);
        gridPane.add(lg, 0, 5, 11, 1);
        gridPane.setAlignment(Pos.CENTER);
        Text zhexy = new Text();
        zhexy.setText("用户协议");
        zhexy.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Text xycontent = new Text();
            xycontent.setTextAlignment(TextAlignment.CENTER);
            xycontent.setFont(Font.font(null,FontWeight.BOLD,15));
            String content=
                    """
                            为使用本软件及服务，您应当阅读并遵守《本软件许可协议》（以下简称（本协议）。请您务必审慎阅读，
                            从分理解各条款内容，特别是免除或者限制责任的条款，以及开通或使用某项服务的单独协议，并选择接受或不接受。限制，免责条款可能以加粗形式提示您注意。
                            除非您已阅读并接受本协议所有条款，否则您无权下载，安装或使用本软件及相关服务。您的下载，安装，登录等使用行为即视为您已阅读并同意上述协议的约束。
                            如果您未满18周岁，请在法定监护人的陪同下阅读本协议及其他上述协议，并特别注意未成年人使用条款。
                            一， 协议的范围
                            本协议是您与本软件之间关于您下载，安装，使用，复制本软件，以及使用本软件相关服务所订立的协议。
                            二， 关于本服务
                            本服务内容是指本软件客户端软件提供包括但不限于WINDOWS,LINUX等多个版本，您必须选择与所安装设备相匹配的软件版本。
                            三， 软件的获取
                            您可以直接从本软件授权的第三方获取。
                            如果您从未经本软件授权的第三方获取本软件或与本软件名称相同的安装程序，本软件无法保证该软件能够正常使用，并对因此给您造成的损失不予负责。下载安装程序后，您需要按照该程序提示的步骤正确安装。
                            为提供更加优质，安全的服务，在本软件安装时本软件可能推荐您安装其他软件，您可以选择安装或不安装。
                            如果您不再需要使用本软件或者需要安装新版本软件，可以自行卸载。
                            四， 软件的更新
                            为了改善用户体验，完善服务内容，本软件将不断努力开发新的服务，并为您不时提供软件更新(这些更新可能会采取软件替换，修改，功能强化，版本升级等形式)。
                            为了保证本软件及服务的安全性和功能的一致性，本软件有权不向您特别通知而对软件进行更新，或者对软件的部分功能效果进行改变或限制。
                            本软件新版本发布后，旧版本的软件可能无法使用，本软件部保证旧版本软件继续可用及相应的服务，请您随时核对并下载最新版本。
                            五， 用户个人信息保护
                            保护用户个人信息是本软件的一项基本原则，本软件将会采取合理的措施保护用户的信息。除法律法规规定的情形外，未经用户许可本软件不会向第三方公开，透漏用户个人信息。
                            为了向用户提供相关服务功能或改善技术和服务，您在注册账号或使用本服务的过程中，可能需要提供一些必要信息，本软件对相关信息采用国际化标准的加密存错与传输方式，保障用户个人信息的安全。
                            未经您的同意，本软件不会向本软件以外的任何公司，组织和个人披露您的个人信息，但法律法规另有规定的除外。
                            本软件非常重视对未成年人个人信息的保护。若您是18周岁以下的未成年人，在使用本软件的服务前，应事先取得您家长或法定监护人的书面同意。
                            六， 主权力义务条款
                            本软件特别提醒您应妥善保管您的账号，当您使用完毕后，应安全退出。
                            用户注意事项：您的理解并同意，为了向您提供有效的服务，您在此许可本软件利用您移动通讯终端设备的处理器和宽带等资源。本软件使用过程中可能产生的数据流量的费用，您需自行向运营商了解相关资费信息。
                            七， 用户行为规范
                            您在使用本服务时需遵守法律法规，社会主义制度，国家利益，公民合法权利，社会公共秩序，道德风尚及信息真实性等“七条底线“要求。
                            八， 软件使用规范
                            除非法律允许或本软件的书面许可，您使用本软件过程中不得删除本软件及其副本上关于知识产权的信息，不得对本软件进行反向工程等或以其他方式尝试发现本软件的源代码。
                            九， 对自己行为负责
                            您充分了解并同意，您必须为自己注册账号下的一切行为负责。
                            十， 其他
                            您使用本软件即视为您已阅读并同意接受本软件协议的约束。本软件有权在必要时修改本协议条款。如果您不接受修改后的条款，应当停止使用本软件。

                            by FANGZHENG FRESHWATER
                            方正著作权所有
                            QQ 1640691243""";
            xycontent.setText(content);
            HBox hBoxxy = new HBox();
            hBoxxy.setAlignment(Pos.CENTER);
            hBoxxy.getChildren().add(xycontent);
            Scene scenexy = new Scene(hBoxxy, 1500, 800);
            Stage stagexy = new Stage();
            stagexy.setScene(scenexy);
            stagexy.alwaysOnTopProperty();
            stagexy.setResizable(false);
            File ico = new File("ico\\alert.png");
            try {
                stagexy.getIcons().add(new Image(new FileInputStream(ico)));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            stagexy.show();
        });
        zhexy.setFont(Font.font(null, FontWeight.BOLD, 13));
        RadioButton yhxy = new RadioButton();
        /*
          默认按钮被选中,测试需要
         */
        yhxy.setSelected(true);
        loginbutton.addEventHandler(MouseEvent.MOUSE_CLICKED, e ->
        {
            if (Objects.equals(e.getButton().toString(), "PRIMARY")) {
                if (!yhxy.isSelected()) {
                    Stage aleat = new Stage();
                    Text err = new Text();
                    err.setText("请先同意用户协议!");
                    err.setTextAlignment(TextAlignment.CENTER);
                    err.setFill(Color.RED);
                    err.setFont(Font.font(null, FontWeight.BOLD, 25));
                    Popbox(aleat, err);
                } else {
                    String getname;
                    try {
                        getname =getCpuId();
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    String getkey = keyfile.getText();
                    String getaddress = addressfile.getText();
                    String getport = portfile.getText();
                    String udpport = "30000";
                    if (Instrument.isIPAdress(getaddress) && Instrument.isport(getport)) {
                        chat(getaddress, getport, getname, getkey, Integer.parseInt(udpport));
                        stage.close();
                    } else {
                        if(!Instrument.isport(getport)) {
                            showalert(getport);
                        }
                        if (!Instrument.isIPAdress(getaddress)) {
                            Stage aleat = new Stage();
                            Text err = new Text();
                            err.setText("IP地址违法,请检查");
                            err.setTextAlignment(TextAlignment.CENTER);
                            err.setFill(Color.RED);
                            err.setFont(Font.font(null, FontWeight.BOLD, 18));
                            Popbox(aleat, err);
                        }
                    }
                }
            }
        });
        HBox xy = new HBox();
        xy.getChildren().add(yhxy);
        xy.getChildren().add(zhexy);
        xy.setPadding(new Insets(0, 15, -230, 0));
        xy.setAlignment(Pos.CENTER);
        gridPane.add(xy, 0, 6, 11, 1);
        Scene scene = new Scene(gridPane, 700, 400);
        stage.setScene(scene);
        stage.setResizable(false);
        File ico = new File("ico\\login.png");
        try {
            stage.getIcons().add(new Image(new FileInputStream(ico)));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        stage.show();
        stage.setOnCloseRequest(event -> System.exit(0));
    }

    public static void showalert(String udpport) {
        if (!Instrument.isport(udpport)) {
            Stage aleat = new Stage();
            Text err = new Text();
            err.setText("端口号违法,请检查是否在1-65536之间");
            err.setTextAlignment(TextAlignment.CENTER);
            err.setFill(Color.RED);
            err.setFont(Font.font(null, FontWeight.BOLD, 15));
            Popbox(aleat, err);
        }
    }

    public static void newalert(Stage alert, Text err) {
        err.setTextAlignment(TextAlignment.CENTER);
        err.setFill(Color.RED);
        err.setFont(Font.font(null, FontWeight.BOLD, 18));
        newbox(alert, err);
    }

    public static void newbox(Stage alert, Text err) {
        HBox theerr = new HBox();
        theerr.setAlignment(Pos.CENTER);
        theerr.getChildren().add(err);
        theerr.setPadding(new Insets(-30, 0, 0, 0));
        Scene scene = new Scene(theerr, 300, 160);
        alert.setScene(scene);
        alert.setResizable(false);
        File ico = new File("ico\\alert.png");
        try {
            alert.getIcons().add(new Image(new FileInputStream(ico)));
        } catch (FileNotFoundException event) {
            throw new RuntimeException(event);
        }
        alert.show();
    }

    public static void Popbox(Stage aleat, Text err) {
        POP(aleat, err);
    }

    public static void POP(Stage aleat, Text err) {
        newbox(aleat, err);
    }

    /**
     * 写一个标志位是否允许通话,
     */

    public static ObservableList<String> data = FXCollections.observableArrayList();
    public static ListView<String> listView = new ListView<>(data);
    public static TextArea inputarea = new TextArea();
    public static ObservableList<Object> getdata = FXCollections.observableArrayList();
    public static ListView<Object> getlist = new ListView<>(getdata);
    public static TextArea infarea = new TextArea();
    public static ConcurrentHashMap<String, String> thefilepath = new ConcurrentHashMap<>();

    public static void chat( String ip, String port, String userid, String key,int udpport) {
        Thread VC = new VideoChat();
        VC.start();
        Path files = Paths.get("Receive");
        try {
            Files.createDirectories(files);
        } catch (IOException e) {
            infarea.appendText("文件夹建立失败,手动创建");
        }
        IP = ip;
        KEY = key;
        PORT = Integer.parseInt(port);
        ID = userid;
        UDPPORT = udpport;
        /*
          向服务器发送在线的线程,直接在开头就会运行
         */
        UdpClientrun(UDPPORT);
        Scheduled_Tasks Scheduled_Tasks = new Scheduled_Tasks(infarea,IP,PORT,ID,thefilepath,getdata,KEY);
        Scheduled_Tasks.Scheduled_Tasks_pool();
        /*
          这个地方从列表点击获取,要从主函数里删掉
         */
        HBox Hmessage = new HBox();//左上角的聊天框
        HBox intitle = new HBox();
        intitle.setMinHeight(30);
        Text listtitle = new Text();
        /*
          List的接口,传用户列表进去
         */
        listView.setMinWidth(330);
        listView.setMaxWidth(330);
        listView.setMinHeight(260);
        listView.getStylesheets().add("the.css");
        listtitle.setText("在线用户列表");
        listView.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends String> ov, String old_val,
                 String new_val) -> {
                    if (new_val != null) {
                        String theid = new_val.split("//")[2];
                        if (!Objects.equals(theid, ID) && !titlelsit.contains(new_val)) {
                            titlelsit.add(new_val);
                            chatone(new_val, null);
                        }
                    }
                });
        listtitle.setFont(Font.font(null, FontWeight.BOLD, 18));
        intitle.getChildren().add(listtitle);
        intitle.setAlignment(Pos.CENTER);
        GridPane top = new GridPane();
        inputarea.setWrapText(true);
        inputarea.getStylesheets().add("the.css");
        getlist.getStylesheets().add("the.css");
        getlist.setMinHeight(260);
        HBox titlebox = new HBox();
        titlebox.setAlignment(Pos.CENTER);
        getlist.setMaxWidth(520);
        getlist.setMinWidth(520);
        Text infareatitle = new Text();
        infareatitle.setText("用户聊天消息");
        infareatitle.setTextAlignment(TextAlignment.CENTER);
        infareatitle.setFill(Color.rgb(70,189,153));
        infareatitle.setFont(Font.font(null, FontWeight.BOLD, 25));
        titlebox.getChildren().add(infareatitle);
        getdata.add(titlebox);
        top.add(getlist,0,0,2,1);
        Button send = new Button();
        /*
          发送消息的按钮接口
         */
        send.setText("发送消息");
        send.setMinWidth(55);
        send.setMinHeight(25);
        send.getStylesheets().add("the.css");
        HBox textk = new HBox();
        /*
          发送文件的按钮接口
         */
        Text JUBAO = new Text("举报");
        JUBAO.addEventHandler(MouseEvent.MOUSE_CLICKED,e->{
                VBox box = new VBox();
                box.setAlignment(Pos.CENTER);
                Text explain = new Text("请填写举报用户信息和违规内容");
                Text text2 = new Text("(不能只发送截图)");
                explain.setFont(Font.font(null, FontWeight.BOLD, 15));
                text2.setFont(Font.font(null, FontWeight.BOLD, 15));
                TextArea area = new TextArea();
                area.getStylesheets().add("the.css");
                area.setWrapText(true);
                area.setMaxWidth(300);
                Button button = new Button();
                HBox thebox = new HBox();
                thebox.setAlignment(Pos.CENTER);
                TextArea filepath = new TextArea();
            filepath.setWrapText(true);
                filepath.setMaxHeight(10);
                filepath.setMaxWidth(300);
                filepath.setEditable(false);
                button.setText("举报");
                Button f2 = new Button("选择截图");
                f2.getStylesheets().add("the.css");
                f2.addEventHandler(MouseEvent.MOUSE_CLICKED, Ee -> {
                    FileChooser fileChooser = new FileChooser();
                    fileChooser.setTitle("选择图片");
                    fileChooser.getExtensionFilters().addAll(
                            new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                            new FileChooser.ExtensionFilter("PNG", "*.png")
                    );
                    File file = fileChooser.showOpenDialog(new Stage());
                    if (file != null) {
                        if ((file.getName()).getBytes(StandardCharsets.UTF_8).length > 60 || file.length() > 104857600) {
                            infarea.appendText("图片名过长或大于2G,请检查");
                        } else {
                            filepath.setText(String.valueOf(file));
                        }
                    }
                });
                button.getStylesheets().add("the.css");
                area.setMinHeight(50);
                box.getChildren().add(explain);
                box.getChildren().add(text2);
                box.getChildren().add(area);
                box.getChildren().add(filepath);
                box.getChildren().add(thebox);
                thebox.getChildren().add(button);
                thebox.getChildren().add(f2);
                Stage stage = new Stage();
                Scene scene = new Scene(box, 300, 200);
                button.addEventHandler(MouseEvent.MOUSE_CLICKED, ed -> {
                    if(System.currentTimeMillis()- Main.interval<=180000)
                    {
                        Stage aleat = new Stage();
                        Text err = new Text();
                        err.setText("请等待三分钟后点击");
                        err.setTextAlignment(TextAlignment.CENTER);
                        err.setFill(Color.RED);
                        err.setFont(Font.font(null, FontWeight.BOLD, 18));
                        Popbox(aleat, err);
                    }
                    else {
                        String inf = area.getText();
                        if (area.getText().getBytes(StandardCharsets.UTF_8).length > 2048) {
                            infarea.appendText("文字过多,请简单描述\n");
                        }
                        if (inf.contains("//")) {
                            infarea.appendText("不允许含有//\n");
                        }
                        if (!inf.contains("//") && !(inf.getBytes(StandardCharsets.UTF_8).length >= 2048)) {
                            if (Objects.equals(filepath.getText(), "")) {
                                sendJB(inf,null,IP,27777);
                            } else {
                                sendJB(inf,filepath.getText(),IP,27777);
                            }
                        }
                        Main.interval=System.currentTimeMillis();
                    }
                });
                stage.setResizable(false);
                stage.setScene(scene);
                File ico = new File("ico\\alert.png");
                try {
                    stage.getIcons().add(new Image(new FileInputStream(ico)));
                } catch (FileNotFoundException E) {
                    throw new RuntimeException(E);
                }
                stage.setTitle("举报");
                stage.show();
        });
        textk.getChildren().add(JUBAO);
        textk.setAlignment(Pos.CENTER);
        textk.setMinWidth(55);
        textk.setMinHeight(30);
        Hmessage.getChildren().add(top);
        top.add(textk, 0, 1, 1, 1);
//        top.add(filesend,1,1,1,1);
        HBox Hgetinput = new HBox();//左下的输入框
        GridPane TEMP = new GridPane();
        TEMP.setMaxWidth(520);
        TEMP.setMinWidth(520);
//        TEMP.setGridLinesVisible(true);
        boxhandel(send, Hgetinput, TEMP, inputarea);
        VBox Userlist = new VBox();//右边的用户列表
        Background hm = new Background(new BackgroundFill(Color.rgb(245, 245, 245), CornerRadii.EMPTY, Insets.EMPTY));
        Hmessage.setBackground(hm);
        BorderPane borderPane = new BorderPane();//自由布局
        Scene scene = new Scene(borderPane, WIDTH, HEIGH);
        send.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            Thread sendmessgaeall = new SendMessageToAll(userlist, inputarea);
            sendmessgaeall.start();
        });
        Interface(Hmessage,top,textk,Hgetinput,scene,getlist);
        Userlist.setMinWidth(330);
        scene.widthProperty().addListener((observable, oldValue, newValue) -> {
            Hmessage.setMinWidth(520 + (double) newValue - WIDTH);
            Hgetinput.setMinWidth(520 + (double) newValue - WIDTH);
            inputarea.setMinWidth(520 + (double) newValue - WIDTH);
            TEMP.setMaxWidth(520 + (double) newValue - WIDTH);
            getlist.setMaxWidth(520+(double)newValue-WIDTH);
            getlist.setMinWidth(520+(double)newValue-WIDTH);
        });
        scene.heightProperty().addListener((observable, oldValue, newValue)-> listView.setMinHeight(260+(double)newValue-HEIGH));
        GridPane bt = new GridPane();
        bt.add(Hgetinput, 0, 0, 1, 1);
        /*
          这里是最右边的文本框,打印后台输出消息
         */
        infarea.setWrapText(true);
        infarea.getStylesheets().add("the.css");
        infarea.setPrefSize(330, 140);
        infarea.setEditable(false);
        bt.add(infarea, 1, 0, 1, 1);
        borderPane.setLeft(Hmessage);
        borderPane.setBottom(bt);
        borderPane.setRight(Userlist);
        Userlist.getChildren().add(intitle);
        Userlist.getChildren().add(listView);
        all.setScene(scene);
        all.setTitle( ID+"   群聊");
        all.setOnCloseRequest(event -> System.exit(0));
        File ico = new File("ico\\Message.png");
        try {
            all.getIcons().add(new Image(new FileInputStream(ico)));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        all.show();
    }

    public static void boxhandel(Button send, HBox hgetinput, GridPane TEMP, TextArea inputarea) {
        HBox HSEND = new HBox();
        HSEND.setAlignment(Pos.CENTER_RIGHT);
        HSEND.getChildren().add(send);
        TEMP.add(inputarea, 0, 0, 1, 1);
        TEMP.add(HSEND, 0, 1, 1, 1);
        hgetinput.getChildren().add(TEMP);
    }

    public static void Interface(HBox hmessage, GridPane top, HBox textk, HBox hgetinput, Scene scene,ListView<Object> getlist) {
        top.setMinHeight(290);
        hgetinput.setMaxHeight(140);
        hgetinput.setMinHeight(140);
        textk.setMaxHeight(30);
        scene.heightProperty().addListener((observable, oldValue, newValue) -> {
            hmessage.setMinHeight(290 + (double) newValue - HEIGH);
            getlist.setMinHeight(260+(double) newValue -HEIGH);
            top.setMinHeight(290 + (double) newValue - HEIGH);
            hgetinput.setMaxHeight(140);
            hgetinput.setMinHeight(140);
        });
        scene.widthProperty().addListener((observable, oldValue, newValue) -> {
            hmessage.setMaxWidth(520+(double)newValue);
        hmessage.setMinWidth(520+(double)newValue);
        hgetinput.setMaxWidth(520+(double)newValue);
        hgetinput.setMinWidth(520+(double)newValue);
        });
        all.setMinHeight(480);
        all.setMinWidth(880);
    }

    public static void chatone(String get, String content) {
        String title = get.split("//")[0]+"//"+get.split("//")[1]+"//"+get.split("//")[2];
        ConcurrentHashMap<String,String> mess = new ConcurrentHashMap<>();
        Path files = Paths.get("Receive\\"+title.split("//")[2]);
        Path file1 = Paths.get("Receive\\"+title.split("//")[2]+"\\"+"file");
        Path file2 = Paths.get("Receive\\"+title.split("//")[2]+"\\"+"image");
        Path file3 = Paths.get("Receive\\"+title.split("//")[2]+"\\"+"voice");
        try {
            Files.createDirectories(files);
            Files.createDirectories(file1);
            Files.createDirectories(file2);
            Files.createDirectories(file3);
        } catch (IOException e) {
            System.out.println("error");
        }
        Stage onepeople = new Stage();
        onepeople.setTitle(title);
        /*
          向服务器发送在线的线程,直接在开头就会运行
         */
        HBox Hmessage = new HBox();//左上角的聊天框
        GridPane top = new GridPane();
        /*
          输入区的接口,获取输入内容
         */
        TextArea inputarea = new TextArea();
        inputarea.setMinWidth(520);
        /*
          输出区的接口,打印别人说的内容
         */
        ObservableList<Object> getdata = FXCollections.observableArrayList();
        ListView<Object> getlist = new ListView<>(getdata);
        HBox titlebox = new HBox();
        titlebox.setAlignment(Pos.CENTER);
        getlist.setMinWidth(520);
        Text infareatitle = new Text();
        infareatitle.setText("聊天消息");
        infareatitle.setTextAlignment(TextAlignment.CENTER);
        infareatitle.setFill(Color.rgb(70,189,153));
        infareatitle.setFont(Font.font(null, FontWeight.BOLD, 25));
        titlebox.getChildren().add(infareatitle);
        getdata.add(titlebox);
        getlist.setEditable(false);
        if (content != null) {
            SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date timed = new Date(); // 获取当前时间
            String formattime = sdfd.format(timed);// 格式化时间
            getdata.add(title.split("//")[2] + ":" + formattime);
            TextArea print = new TextArea();
            print.setWrapText(true);
            print.setEditable(false);
            print.setPrefSize(500,100);
            print.setStyle("-fx-font-size: 18 ;-fx-font-weight:bold");
            print.setText(content);
            getdata.add(print);
            getlist.setItems(getdata);
            InfOperation.Sendtheinf(get.split("//"),"messageget",get.split("//")[3].getBytes(StandardCharsets.UTF_8));
        }
        inputarea.setWrapText(true);
        inputarea.getStylesheets().add("the.css");
        getlist.getStylesheets().add("the.css");
        getlist.setMinHeight(260);
        top.add(getlist, 0, 0, 2, 1);
        Button send = new Button();
        /*
          发送消息的按钮接口
         */
        send.setText("发送消息");
        send.addEventHandler(MouseEvent.MOUSE_CLICKED, e ->
        {
            Thread sendoneuser = new SendMessageToOne(inputarea, title, getdata,getlist,mess);
            sendoneuser.start();
        });
        send.setMinWidth(55);
        send.setMinHeight(25);
        send.getStylesheets().add("the.css");
        HBox textk = new HBox();
        /*
          发送文件的按钮接口
         */
        Text functiondes = new Text(" 发送文件  ");
        Text f2 = new Text("  发送图片  ");
        Text f3 = new Text("  发送语音  ");
        Text f4 = new Text("  视频聊天  ");
        f4.addEventHandler(MouseEvent.MOUSE_CLICKED,e->{
            /*
              有一个标记,如果存在这个标记,就已经进行通话了,
             */
            Sendtheinf(title.split("//"),"messageone//"+System.currentTimeMillis(),"想与您进行视频通话".getBytes(StandardCharsets.UTF_8));
            Sendtheinf(title.split("//"),"want",null);
        });
        functiondes.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("选择文件");
            File file = fileChooser.showOpenDialog(new Stage());
            if(file!=null) {
                if((file.getName()).getBytes(StandardCharsets.UTF_8).length>60||file.length()>2146483648L)
                {
                    infarea.appendText("文件名过长或文件大于2G,请检查");
                }
                else{ if (thefilepath.containsKey(file.getName())) {
                        infarea.appendText("文件已经发送,不允许重复发送");
                    } else {
                        if(Main.LIVE==1)
                        {
                            infarea.appendText("请等待语音聊天结束");
                        }
                        else {
                            String flag = "file";
                            Thread sendfile = new SendFile(file, title, thefilepath, flag);
                            sendfile.start();
                        }
                    }
                }
            }
        });
        /*
          发图片
         */
        f2.addEventHandler(MouseEvent.MOUSE_CLICKED, e->{
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("选择图片");
            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("GIF", "*.gif"),
                new FileChooser.ExtensionFilter("BMP", "*.bmp"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
            );
            File file = fileChooser.showOpenDialog(new Stage());
            if(file!=null) {
                if((file.getName()).getBytes(StandardCharsets.UTF_8).length>60||file.length()>2146483648L)
                {
                    infarea.appendText("文件名过长或文件大于2G,请检查");
                }
                else{
                    if (thefilepath.containsKey(file.getName())) {
                        infarea.appendText("图片已经发送,不允许重复发送");
                    }
                    else {
                        if(Main.LIVE==1)
                        {
                            infarea.appendText("请等待语音聊天结束");
                        }
                        else {
                            String flag = "image";
                            Thread sendfile = new SendFile(file, title, thefilepath, flag);
                            sendfile.start();
                        }
                    }
                }
            }
        });
        f3.addEventHandler(MouseEvent.MOUSE_CLICKED,e->{
            if(Main.LIVE==1)
            {
                infarea.appendText("请等待语音聊天结束");
            }
            else {
                try {
                    Acoustics.record(title, thefilepath);
                } catch (LineUnavailableException | InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        textk.getChildren().add(functiondes);
        textk.getChildren().add(f2);
        textk.getChildren().add(f3);
        textk.getChildren().add(f4);
        textk.setAlignment(Pos.CENTER);
        textk.setMinWidth(55);
        textk.setMinHeight(25);
        Hmessage.getChildren().add(top);
        top.add(textk, 0, 1, 1, 1);
        HBox Hgetinput = new HBox();//左下的输入框
        GridPane TEMP = new GridPane();
        TEMP.setMaxWidth(520);
        boxhandel(send, Hgetinput, TEMP, inputarea);
        Background hm = new Background(new BackgroundFill(Color.rgb(245, 245, 245), CornerRadii.EMPTY, Insets.EMPTY));
        Hmessage.setBackground(hm);
        BorderPane borderPane = new BorderPane();//自由布局
        Scene scene = new Scene(borderPane, 520, HEIGH);
        Interface(Hmessage, top, textk, Hgetinput, scene, getlist);
        GridPane bt = new GridPane();
        bt.add(Hgetinput, 0, 0, 1, 1);
        borderPane.setLeft(Hmessage);
        borderPane.setBottom(bt);
        onepeople.setScene(scene);
        Scheduled_Tasks_one Scheduled_Tasks_one = new Scheduled_Tasks_one(getdata,getlist,title,mess);
        Scheduled_Tasks_one.Scheduled_Tasks_poll();
        getlist.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<?> ov, Object old_val,
                 Object new_val) -> {
                    if (new_val != null) {
                        if (String.valueOf(new_val).contains("Receive\\")&&String.valueOf(new_val).endsWith(".wav"))
                        {
                            try {
                                infarea.appendText("播放录音\n");
                                new Thread(()-> playfile(String.valueOf(new_val))).start();
                            }catch (Exception e)
                            {
                                infarea.appendText("不是录音,不允许点击\n");
                            }
                        }
                    }
                });
        onepeople.setOnCloseRequest(event -> {
            titlelsit.remove(title);
            Scheduled_Tasks_one.close();
            });
        scene.widthProperty().addListener((observable, oldValue, newValue) -> {
            getlist.setMinWidth((double)newValue);
            inputarea.setMinWidth((double)newValue);
        });
        onepeople.setMinWidth(520);
        File ico = new File("ico\\solomessage.png");
        try {
            onepeople.getIcons().add(new Image(new FileInputStream(ico)));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        onepeople.show();
    }
}
