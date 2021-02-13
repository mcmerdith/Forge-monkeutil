package net.mcmerdith.monkeutil.client.util;

import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * Utilitie functions for Client side use
 *
 * @author mcmerdith
 * @version 1.0
 * @since 1.0
 */
@OnlyIn(Dist.CLIENT)
public class ClientUtils {
    /**
     * Get a TranslationTextComponent for a tooltip
     *
     * @param tooltipName The name of the tooltip
     * @return A text component with the translation key matching 'tooltip.monkeutil.[tooltipName]'
     */
    public static TranslationTextComponent tooltipTranslation(String tooltipName) {
        return translation("tooltip", tooltipName);
    }

    /**
     * Get a TranslationTextComponent for a screen
     *
     * @param screenName The name of the screen
     * @return A text component with the translation key matching 'screen.monkeutil.[screenName]'
     */
    public static TranslationTextComponent screenTranslation(String screenName) {
        return translation("screen", screenName);
    }

    /**
     * Get a TranslationTextComponent for a button
     *
     * @param screen     The name of the screen this button is attached to
     * @param buttonName The name of the button
     * @return A text component with the translation key matching '[screen].monkeutil.button.[buttonName]'
     */
    public static TranslationTextComponent buttonTranslation(String screen, String buttonName) {
        return translation("screen", "button." + buttonName);
    }

    /**
     * Get a TranslationTextComponent
     *
     * @param type  The type string
     * @param field The name of the field
     * @return A text component with the translation key matching '[type].monkeutil.[field]'
     */
    public static TranslationTextComponent translation(String type, String field) {
        return new TranslationTextComponent(type + ".monkeutil." + field);
    }
}
