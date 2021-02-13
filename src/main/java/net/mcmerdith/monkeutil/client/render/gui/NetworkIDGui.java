package net.mcmerdith.monkeutil.client.render.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.mcmerdith.monkeutil.Main;
import net.mcmerdith.monkeutil.client.util.ClientUtils;
import net.mcmerdith.monkeutil.core.networking.IWirelessBlock;
import net.mcmerdith.monkeutil.core.networking.NetworkManager;
import net.mcmerdith.monkeutil.core.networking.NetworkState;
import net.mcmerdith.monkeutil.core.networking.NetworkStorage;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

/**
 * GUI to edit the ID of a WirelessBlock
 * Not accessible on a server jar
 *
 * @author mcmerdith
 * @version 1.0
 * @since 1.0
 */
@OnlyIn(Dist.CLIENT)
public class NetworkIDGui extends BaseScreen {
    /**
     * If the inputted ID was invalid
     */
    private boolean invalid = false;
    /**
     * The ID input field
     */
    private TextFieldWidget input;
    /**
     * The WirelessBlock we're modifying
     */
    private final IWirelessBlock tileentity;

    /**
     * Create a GUI to edit the network ID of a WirelessBlock
     *
     * @param tileentity The WirelessBlock to modify
     */
    protected NetworkIDGui(IWirelessBlock tileentity) {
        super("network_id", 246, 65);
        this.tileentity = tileentity;
    }

    @Override
    protected void initialize(int xStart, int yStart) {
        // Get the Network object related to this GUI
        NetworkStorage storage = tileentity.getNetworkStorage();

        // Add the ID input to renderComponents
        input = new TextFieldWidget(this.font, xStart + 10, getYForRow(0), WIDTH - 70, 20, ClientUtils.translation("placeholder", "network_id"));
        input.setText(String.valueOf(storage.networkID));
        renderComponents.add(input);

        // Add the save button
        addButton(new Button(getXRightAlign(40), getYForRow(0), 40, 20, ClientUtils.buttonTranslation("general", "save"), button -> {
            // Get the text from the input
            String temp = input.getText();

            // Attempt getting an integer
            try {
                int id = Integer.parseInt(temp);
                // If out of range, invalid
                if (id < 1 || id >= NetworkManager.NETWORK_ID_RANGE) invalid = true;
                else {
                    // Reset the storage component with the new ID
                    storage.update(NetworkState.IDLE, id);
                    // Close the GUI on success
                    BaseScreen.close();
                }
            } catch (NumberFormatException e) {
                // Not a number, invalid
                invalid = true;
            }
        }));
    }

    @Override
    public void render(@Nonnull MatrixStack stack, int x, int y, float ticks) {
        if (invalid)
            drawString(stack, this.font, ClientUtils.translation("string", "invalid_network_id"), getGUIStartX() + 10, getGUIStartY() + 20, this.defaultColor);
        super.render(stack, x, y, ticks);
    }

    /**
     * Open a GUI to edit a network object
     *
     * @param tileentity The network object to edit
     * @apiNote If the provided TileEntity is NOT an instance of IWirelessBlock, it will fail silently and do nothing
     */
    public static void open(TileEntity tileentity) {
        if (tileentity instanceof IWirelessBlock) BaseScreen.open(new NetworkIDGui((IWirelessBlock) tileentity));
        else Main.LOGGER.error("Tried to open NetworkIDGui on non-IWirelessBlock");
    }
}
