package Console;

import Uniwork.Appl.NGApplication;
import Uniwork.Appl.NGCustomApplicationModule;
import Uniwork.Appl.NGVisualApplicationModule;

import java.util.Iterator;

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
        registerObjectRequest("TestConsole", this, "Test", "TestTheBest", "This is a test method.");
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

    public static void main(String[] args) {
        launch(args);
    }

}
