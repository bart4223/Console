package Console;

import Uniwork.Appl.NGApplication;
import Uniwork.Appl.NGCustomApplicationModule;
import Uniwork.Appl.NGVisualApplicationModule;

import java.util.Iterator;

import static Uniwork.Misc.NGLogObject.CreateColorMessage;
import static Uniwork.Misc.NGLogObject.CreateSimpleMessage;
import static Uniwork.Misc.NGLogObject.CreateScriptMessage;

public class Main extends NGApplication {

    @Override
    public void DoBeforeInitialize() {
        super.DoBeforeInitialize();
        NGVisualApplicationModule main = (NGVisualApplicationModule)addModule(MainApplicationModule.class, "Main");
        main.setPrimaryStage(FPrimaryStage);
    }

    @Override
    protected void registerObjectRequests() {
        super.registerObjectRequests();
        registerObjectRequest("Application", this, "T", "TestTheBest", "This is a test the best method.");
        registerObjectRequest("Application", this, "TL", "TestLog", "This is a test log method.");
    }

    public Main() {
        super();
        FName = "Test-Console";
        FDescription = "Test-Console is a project for testing the console";
        FConfigurationFilename = "resources/config.acf";
    }

    public void TestTheBest() {
        Iterator<NGCustomApplicationModule> itr = FModuleManager.getModules();
        while (itr.hasNext()) {
            NGVisualApplicationModule mod = (NGVisualApplicationModule)itr.next();
            writeInfo(String.format("%s: %s", mod.getFullName(), mod.getStageCount()));
        }
    }

    public void TestLog() {
        writeInfo(CreateSimpleMessage("Simple message..."));
        writeInfo(CreateColorMessage("Color message...", "0000FF"));
        writeInfo(CreateScriptMessage("Script message...", "Application.ShowMessage Cool"));
        writeInfo(CreateScriptMessage("Script color message...", "00FF00", "Color", "Application.ShowMessage \"Cool Color\""));
        writeInfo(CreateColorMessage("Color message...", "FF0080"));
        writeInfo(CreateScriptMessage("Script message exit...", "Application.Exit"));
    }

    public static void main(String[] args) {
        launch(args);
    }

}
