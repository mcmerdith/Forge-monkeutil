package net.mcmerdith.monkeutil.client.render.gui;

import net.mcmerdith.monkeutil.client.util.ClientUtils;
import net.minecraft.client.gui.widget.button.Button;

/**
 * GUI to view all active instances of WirelessBlock
 * Not accessible on a server jar
 *
 * @author mcmerdith
 * @version 1.0
 * @since 1.0
 */
public class RemoteControlGUI extends BaseScreen {
    /**
     * Create a new RemoteControlGUI
     */
    protected RemoteControlGUI() {
        super("remote_control", 246, 164);
    }

    @Override
    protected void initialize(int xStart, int yStart) {
        addButton(new Button(xStart + 10, yStart + 20, 160, 20, ClientUtils.buttonTranslation("remote_control", "test"), button -> {
        }));
    }

    /**
     * Open this GUI
     */
    public static void open() {
        BaseScreen.open(new RemoteControlGUI());
    }
}
