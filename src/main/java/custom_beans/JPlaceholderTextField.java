package custom_beans;

import com.google.common.flogger.FluentLogger;

import javax.swing.*;
import javax.swing.text.Document;
import java.awt.*;


/**
 * Un JTextField con capacidad de mostrar un texto como placeholder, antes de que se escriba algo (a la Android)
 */
public class JPlaceholderTextField extends JTextField {
	private static final FluentLogger logger = FluentLogger.forEnclosingClass();
	private String placeholder;

	/**
	 * Crea un JPlaceholderTextField sin especificar nada
	 */
	public JPlaceholderTextField() {
	}

	/**
	 * Construye un JPlaceholderTextField que usa un dado text storage model y una dada cantidad de columnas.
	 *
	 * @param doc     el almacenamiento inicial a usar, si es null un default sera proveído al llamar el método createDefaultModel
	 * @param text    el string inicial a mostrar, o null
	 * @param columns la cantidad de columnas a usar para calcular el ancho preferido >= 0; si las columnas son cero, el ancho preferido sera cualquier resultado natural de la implementación del componente
	 */
	public JPlaceholderTextField(
			final Document doc, final String text, final int columns
	                            ) {
		super(doc, text, columns);
	}

	/**
	 * Construye un nuevo PlaceholderTextField con la cantidad especificada de columnas.
	 *
	 * @param columns el numero de columnas a usar para calcular el ancho preferido
	 */
	public JPlaceholderTextField(final int columns) {
		super(columns);
	}

	/**
	 * Construye un PlaceholderTextField con el texto especificado.
	 *
	 * @param text el texto a ser mostrado
	 */
	public JPlaceholderTextField(final String text) {
		super(text);
	}

	/**
	 * Construye un PlaceholderTextField inicializado con un texto y columnas especificas.
	 *
	 * @param text    el texto a ser mostrado
	 * @param columns el numero de columnas a usar para calcular el ancho preferido
	 */
	public JPlaceholderTextField(final String text, final int columns) {
		super(text, columns);
	}

	/**
	 * Obtiene el texto que esta siendo usado como placeholder.
	 *
	 * @return placeholder actual
	 */
	public String getPlaceholder() {
		return placeholder;
	}

	/**
	 * Configura el placeholder a ser usado como
	 *
	 * @param placeholder El placeholder que va a mostrar
	 */
	public void setPlaceholder(final String placeholder) {
		this.placeholder = placeholder;
	}


	@Override
	protected void paintComponent(final Graphics graphics) {
		super.paintComponent(graphics);
		if (placeholder == null || placeholder.length() == 0 || getText().length() > 0) {
			return;
		}
		final Graphics2D graphics2D = (Graphics2D) graphics;
		graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphics2D.setColor(getDisabledTextColor());
		graphics2D.drawString(placeholder, getInsets().left, graphics.getFontMetrics().getMaxAscent() + getInsets().top);
	}

}
