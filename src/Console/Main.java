package Console;

import Uniwork.Appl.NGApplication;
import Uniwork.Appl.NGCustomApplicationModule;
import Uniwork.Appl.NGVisualApplicationModule;
import Uniwork.Base.NGObjectRequestMethod;

import java.text.DecimalFormat;
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
        NGObjectRequestMethod orm;
        orm = registerObjectRequest("Application", this, "Test", "TestTheBest", "This is a test the best method.");
        orm.setAlias("T");
        orm = registerObjectRequest("Application", this, "TestLog", "TestLog", "This is a test log method.");
        orm.setAlias("TL");
        orm = registerObjectRequest("Application", this, "D", "TestgetDurationAsString", "Get Duration as String");
        orm.setAlias("TD");
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

    public void TestgetDurationAsString() {
        writeInfo(getDurationAsString(42));
        writeInfo(getDurationAsString(120));
        writeInfo(getDurationAsString(500));
        writeInfo(getDurationAsString(3500));
        writeInfo(getDurationAsString(3600));
        writeInfo(getDurationAsString(3834));
    }

    protected static String getDurationAsString(Integer aDuration) {
        double hh = Math.floor( aDuration / 3600 );
        double mm = Math.floor( (aDuration%3600) / 60 );
        double ss = Math.floor( aDuration%60 );
        DecimalFormat format = new DecimalFormat("00");
        return String.format("%s:%s:%s", format.format(hh), format.format(mm), format.format(ss));
    }

    public static void main(String[] args) {
        launch(args);
    }

}
