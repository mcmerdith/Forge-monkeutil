package net.mcmerdith.monkeutil.client.render.gui;

import net.mcmerdith.monkeutil.client.util.ClientUtils;
import net.minecraft.client.gui.widget.button.Button;

public class RemoteControlGUI extends BaseScreen {
    protected RemoteControlGUI() {
        super("remote_control", 246, 164);
    }

    @Override
    protected void init() {
        int start = (this.width - WIDTH) / 2;
        int finish = (this.height - HEIGHT) / 2;
        addButton(new Button(start + 10, finish + 10, 160, 20, ClientUtils.buttonTranslation("remote_control", "test"),button -> {}));
    }

    public static void open() {
        BaseScreen.open(new RemoteControlGUI());
    }
}
