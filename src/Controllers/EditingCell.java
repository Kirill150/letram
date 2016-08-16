package Controllers;

import Objects.Driver;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;

/**
 * Created by Papai on 2016.08.13..
 */
public class EditingCell extends TableCell<Driver, String> {




    private TextField textField;

    public EditingCell() {
    }

    @Override
    public void startEdit() {
        if (!isEmpty()) {
            super.startEdit();
            createTextField();
            setText(null);
            setGraphic(textField);
            textField.selectAll();
            System.out.println("StartEditing");
        }
    }

    @Override
    public void cancelEdit() {
        System.out.println("CancelEditing");
        super.cancelEdit();

        setText((String) getItem());
        setGraphic(null);
    }

    @Override
    public void updateItem(String item, boolean empty) {
        System.out.println("UpdateEditing");
        super.updateItem(item, empty);

        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            if (isEditing()) {
                if (textField != null) {
                    textField.setText(getString());
                }
                setText(null);
                setGraphic(textField);
            } else {
                setText(getString());
                setGraphic(null);
            }
        }
    }

    private void createTextField() {
        System.out.println("CreaetTextFeildEditing");
        textField = new TextField(getString());
        textField.setMinWidth(this.getWidth() - this.getGraphicTextGap()* 2);
        textField.focusedProperty().addListener(new ChangeListener<Boolean>(){
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0,
                                Boolean arg1, Boolean arg2) {
                System.out.println("ChangedEditing");
                if (!arg2) {
                    commitEdit(textField.getText());
                }
            }
        });
    }

    private String getString() {
        System.out.println("GetstringEditing");
        return getItem() == null ? "" : getItem().toString();
    }
}

