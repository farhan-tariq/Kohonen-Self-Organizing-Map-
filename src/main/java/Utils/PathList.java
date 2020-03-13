package Utils;

public class PathList {
    private static final String PATH = "/FXML/";
    private static final String DATA_SELECTION_PATH = PATH.concat("DataSelection/");
    private static final String VISUALIZATION_PATH = PATH.concat("Visualization/");
    private static final String SETTINGS_PATH = PATH.concat("Settings/");
    private static final String LEARNING_PATH = PATH.concat("Learning/");
    private static final String SIMULATION_PATH = LEARNING_PATH.concat("Simulation/");
    private static final String TUTORIAL_PATH = LEARNING_PATH.concat("TutorialScreens/");

    private static final String HOME_SCREEN_MENU_FXML = PATH.concat("HomeScreenMenu.fxml");

    private static final String VISUALIZATION_SELECTION_MENU_FXML = VISUALIZATION_PATH.concat("VisualizationSelectionMenu.fxml");
    private static final String TRAINING_VISUALIZATION_FXML = VISUALIZATION_PATH.concat("TrainingVisualization.fxml");
    private static final String HIT_MAP_FXML = VISUALIZATION_PATH.concat("HitmapVisualization.fxml");
    private static final String BMU_MAP_FXML = VISUALIZATION_PATH.concat("BMUVisualization.fxml");

    private static final String TRAINING_TESTING_SELECTION_MENU_FXML = PATH.concat("TrainTestSelectionMenu.fxml");
    private static final String LOAD_TESTING_SAMPLE_DATA_FXML = DATA_SELECTION_PATH.concat("LoadTestSampleData.fxml");
    private static final String LOAD_TRAINING_SAMPLE_DATA_FXML = DATA_SELECTION_PATH.concat("LoadTrainSampleData.fxml");
    private static final String LOAD_TRAINING_DATA_FXML = DATA_SELECTION_PATH.concat("TrainDataSelectionMenu.fxml");
    private static final String LOAD_TESTING_DATA_FXML = DATA_SELECTION_PATH.concat("TestDataSelectionMenu.fxml");

    private static final String TESTING_SETTING_URL = SETTINGS_PATH.concat("TestSettings.fxml");
    private static final String TRAINING_SETTING_URL = SETTINGS_PATH.concat("TrainSettings.fxml");

    private static final String LEARNING_SELECTION_MENU_FXML = LEARNING_PATH.concat("SelectionMenu.fxml");
    private static final String SIMULATION_FXML = SIMULATION_PATH.concat("AlgorithmSimulation.fxml");

    private static final String INTRO_PAGE_FXML = TUTORIAL_PATH.concat("Intro.fxml");
    private static final String LEARNING_URL = TUTORIAL_PATH.concat("Learning.fxml");
    private static final String NEURON_FXML = TUTORIAL_PATH.concat("Neuron.fxml");
    private static final String HEBBIAN_FXML = TUTORIAL_PATH.concat("HebbianLearning.fxml");
    private static final String NORMALIZED_HEBBIAN_FXML = TUTORIAL_PATH.concat("NormalizedHebbianLearning.fxml");
    private static final String SELF_ORGANIZING_MAP_FXML = TUTORIAL_PATH.concat("SOMLearning.fxml");
    private static String DISTANCE_METRIC_FXML = TUTORIAL_PATH.concat("DistanceMetrics.fxml");
    private static String VISUALIZING_SOM_FXML = TUTORIAL_PATH.concat("VisualizingSOM.fxml");

    private static String MULTIPLE_CHOICE_QUESTIONS_FXML = LEARNING_PATH.concat("MCQ/MCQs.fxml");
    private static String MCQ_MAIN_FXML = LEARNING_PATH.concat("MCQ/Main.fxml");
    private static String MCQ_RESULT_FXML = LEARNING_PATH.concat("MCQ/Result.fxml");

    private static final String TRAINING_DATASET1_URL = "src/main/resources/Dataset/Training Dataset/Dataset-1 Numbers (10 Samples Each)";
    private static final String TRAINING_DATASET2_URL = "src/main/resources/Dataset/Training Dataset/Dataset-1 Numbers (20 Samples Each)";
    private static final String TRAINING_DATASET3_URL = "src/main/resources/Dataset/Training Dataset/Dataset-2 Alphabets";

    private static final String TESTING_DATASET1_URL = "src/main/resources/Dataset/Testing Dataset/Dataset-1  Numbers";
    private static final String TESTING_DATASET2_URL = "src/main/resources/Dataset/Testing Dataset/Dataset-2  Alphabets";

    public static String getVisualizationSelectionMenuFxml() {
        return VISUALIZATION_SELECTION_MENU_FXML;
    }

    public static String getHomeScreenMenuFxml() {
        return HOME_SCREEN_MENU_FXML;
    }

    public static String getLoadTestingSampleDataFxml() {
        return LOAD_TESTING_SAMPLE_DATA_FXML;
    }

    public static String getLoadTrainingSampleDataFxml() {
        return LOAD_TRAINING_SAMPLE_DATA_FXML;
    }

    public static String getTrainingVisualizationFxml() {
        return TRAINING_VISUALIZATION_FXML;
    }

    public static String getHitMapFxml() {
        return HIT_MAP_FXML;
    }

    public static String getbmuMapFxml() {
        return BMU_MAP_FXML;
    }

    public static String getLearningUrl() {
        return LEARNING_URL;
    }

    public static String getTrainingTestingSelectionMenuFxml() {
        return TRAINING_TESTING_SELECTION_MENU_FXML;
    }

    public static String getTestingSettingUrl() {
        return TESTING_SETTING_URL;
    }

    public static String getTrainingSettingUrl() {
        return TRAINING_SETTING_URL;
    }

    public static String getSimulationFxml() {
        return SIMULATION_FXML;
    }

    public static String getLearningSelectionMenuFxml() {
        return LEARNING_SELECTION_MENU_FXML;
    }

    public static String getNeuronFxml() {
        return NEURON_FXML;
    }

    public static String getHebbianFxml() {
        return HEBBIAN_FXML;
    }

    public static String getNormalizedHebbianFxml() {
        return NORMALIZED_HEBBIAN_FXML;
    }

    public static String getSelfOrganizingMapFxml() {
        return SELF_ORGANIZING_MAP_FXML;
    }

    public static String getDistanceMetricFxml() {
        return DISTANCE_METRIC_FXML;
    }

    public static String getIntroPageFxml() {
        return INTRO_PAGE_FXML;
    }

    static String getTrainingDataset1Url() {
        return TRAINING_DATASET1_URL;
    }

    static String getTrainingDataset2Url() {
        return TRAINING_DATASET2_URL;
    }

    static String getTestingDataset1Url() {
        return TESTING_DATASET1_URL;
    }

    static String getTestingDataset2Url() {
        return TESTING_DATASET2_URL;
    }

    static String getTrainingDataset3Url() {
        return TRAINING_DATASET3_URL;
    }


    public static String getLoadTrainingDataFxml() {
        return LOAD_TRAINING_DATA_FXML;
    }

    public static String getLoadTestingDataFxml() {
        return LOAD_TESTING_DATA_FXML;
    }

    public static String getNeuronFormatImageURL() {
        return "src/main/resources/Images/NeuronModel.JPG";
    }

    public static String getNeuronModel_ImageURL() {
        return "src/main/resources/Images/Neuron Formula.JPG";
    }

    public static String getMultipleChoiceQuestionsFxml() {
        return MULTIPLE_CHOICE_QUESTIONS_FXML;
    }

    public static String getMcqMainFxml() {
        return MCQ_MAIN_FXML;
    }

    public static String getMCQResultsFxml() {
        return MCQ_RESULT_FXML;
    }

    public static String getVisualizingSomFxml() {
        return VISUALIZING_SOM_FXML;
    }
}
