/**
 * 
 */
/**
 * @author User
 *
 */
module syagui {
	requires javafx.graphics;
	requires javafx.controls;
	requires java.sql;
	requires jfxtras.labs;
	requires javafx.base;
	exports finalprojectbadlab to  javafx.control,javafx.graphics,javafx.fxml,jfxtras.labs;
	opens finalprojectbadlab;
	opens watch;
}