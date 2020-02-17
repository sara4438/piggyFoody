//public class Rankking extends Scene {
//
//    private CommandSolver.MouseCommandListener mouseCommandListener;
//    private static int LEVEL_NUM = 3; 
//    private Background background;
//    private BufferedImage img;
//    private ArrayList<ScoreRecord> scoreRecords;
//    private Button[] levelButton;
//    private Button returnButton;
//    private int currentSelect;
//
//    public Rankking(SceneController sceneController) {
//        super(sceneController);
//        currentSelect = 1;
//    }
//
//    public Rankking(SceneController sceneController, ArrayList<ScoreRecord> scoreRecords, int level) {
//        super(sceneController);
//        this.scoreRecords = scoreRecords;
//        currentSelect = level;
//        sort();
//    }
//
//    @Override
//    public void sceneBegin() {
//        background = new Background();
//        ImageResourceController irc = ImageResourceController.getInstance();
//        img = irc.tryGetImage(PathBuilder.getImg(ImagePath.Background.RANKING));
//        mouseCommandListener = new CommandSolver.MouseCommandListener() {
//            @Override
//            public void mouseTrig(MouseEvent e, CommandSolver.MouseState state, long trigTime) {
//                for (int i = 0; i < LEVEL_NUM; i++) {
//                    if (state == CommandSolver.MouseState.CLICKED) {
//                        if (levelButton[i].isCollision(e.getX(), e.getY())) {
//                            currentSelect = i + 1;
//                            levelButton[i].click(e.getX(), e.getY());
//                        }
//                    } else if (state == CommandSolver.MouseState.MOVED) {
//                        if (levelButton[i].isCollision(e.getX(), e.getY())) {
//                            levelButton[i].hover(e.getX(), e.getY());
//                        }
//                    }
//                }
//                if (state == CommandSolver.MouseState.CLICKED) {
//                    if (returnButton.isCollision(e.getX(), e.getY())) {
//                        returnButton.click(e.getX(), e.getY());
//                    }
//                } else if (state == CommandSolver.MouseState.MOVED) {
//                    if (returnButton.isCollision(e.getX(), e.getY())) {
//                        returnButton.hover(e.getX(), e.getY());
//                    }
//                }
//
//            }
//        };
//        returnButton = new Button(30, 30, 11);
//        returnButton.setButtonListener(new Button.ButtonListener() {
//            @Override
//            public void onClick(int x, int y) {
//                sceneController.changeScene(new MenuScene(sceneController));
//            }
//
//            @Override
//            public void hover(int x, int y) {
//            }
//        });
//        levelButton = new Button[LEVEL_NUM];
//        for (int i = 0; i < LEVEL_NUM; i++) {
//            levelButton[i] = new Button(93, 225 + i * 153, 230, 153, 22, i);
//            levelButton[i].setButtonListener(new Button.ButtonListener() {
//                @Override
//                public void onClick(int x, int y) {
//
//                }
//
//                @Override
//                public void hover(int x, int y) {
//                }
//            });
//        }
//    }
//
//    @Override
//    public void sceneUpdate() {
//        background.moveAction(0, 0);
//    }
//
//    @Override
//    public void sceneEnd() {
//    }
//
//    public void sort() {
//        for (int i = 1; i < scoreRecords.size(); i++) {
//            for (int j = 0; j < scoreRecords.size() - i; j++) {
//                if (scoreRecords.get(j).getPassSec() > scoreRecords.get(j + 1).getPassSec()) {
//                    ScoreRecord tmp = scoreRecords.get(j);
//                    scoreRecords.set(j, scoreRecords.get(j + 1));
//                    scoreRecords.set(j + 1, tmp);
//                }
//            }
//        }
//    }
//
//    @Override
//    public void paint(Graphics g) {
//        background.paint(g);
//        g.drawImage(img, 37, 46, null);
//        returnButton.paintSheet(g);
//        for (int i = 0; i < LEVEL_NUM; i++) {
//            levelButton[i].paintRanks(g);
//        }
//        int tmpNum = 0;
//        g.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 35));
//        
//        
//        for (int i = 0; i < scoreRecords.size(); i++) {
//            ScoreRecord record = scoreRecords.get(i);
//            if (record.getLevel() == currentSelect) {
//                g.drawString(record.getName(), 500, 325 + tmpNum * 153);
//                g.drawString(record.getPasstime().print(), 770, 325 + tmpNum * 153);
//                tmpNum++;
//            }
//            if (tmpNum >= 3) {
//                break;
//            }
//        }
//    }
//
//    @Override
//    public CommandSolver.MouseCommandListener getMouseCommandListener() {
//        return mouseCommandListener;
//    }
//}