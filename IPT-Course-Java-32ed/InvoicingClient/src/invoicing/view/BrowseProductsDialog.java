package invoicing.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.rmi.RemoteException;

import javax.swing.CellEditor;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;

import invoicing.controller.ItemController;
import invoicing.entity.Item;
import invoicing.rmi.Invoicing;

public class BrowseProductsDialog extends JDialog {
	final private MainWindow parent;

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private ProductTableModel tableModel;
	JButton btnEdit = new JButton("Edit");
	private boolean inEditMode;

	/**
	 * Create the dialog.
	 */
	public BrowseProductsDialog(JFrame topFrame, MainWindow parent) {
		super(topFrame, "Browse Product", true);

		this.parent = parent;
		setSize(600, 400);
		setLocationRelativeTo(null);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		tableModel = new ProductTableModel();
		table = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(table);
		contentPanel.add(scrollPane);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		btnEdit.addActionListener(event -> {
			if (!inEditMode) {
				int[] selected = table.getSelectedRows();
				if (selected.length != 1) {
					JOptionPane.showMessageDialog(this, "Exactly one product should be selected for editing.",
							"Invalid Product Selection", JOptionPane.ERROR_MESSAGE);
					return;
				}
				int modelRow = table.convertRowIndexToModel(selected[0]);
				tableModel.setEditable(modelRow);
				inEditMode = true;
				btnEdit.setText("Save");
			} else {
				if (table.isEditing()) { // finish editing and save changes in
											// currently edited field
					CellEditor editor = table.getColumnModel().getColumn(table.getEditingColumn()).getCellEditor();
					if (editor == null)
						editor = table.getDefaultEditor(
								table.getColumnModel().getColumn(table.getEditingColumn()).getClass());
					editor.stopCellEditing();
				}
				tableModel.confirmEdit();
				// parent.getServer().writeProductsToFile();
				inEditMode = false;
				btnEdit.setText("Edit");
			}
		});

		btnEdit.setActionCommand("Edit");
		buttonPane.add(btnEdit);
		getRootPane().setDefaultButton(btnEdit);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);

	}

	public void refresh() {
		tableModel.fireTableDataChanged();
	}

	private class ProductTableModel extends AbstractTableModel {
		private final Invoicing server = parent.getServer();
		private Integer editableRow;
		private Item oldRowItem;

		@Override
		public int getColumnCount() {
			return Item.FIELD_NAMES.length;
		}

		public void setEditable(Integer modelRow) {
			editableRow = modelRow;
			if (modelRow != null)
				try {
					oldRowItem = server.getAllProducts().get(modelRow);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
		}

		public void confirmEdit() {
			if (editableRow != null) {
				fireTableRowsUpdated(editableRow, editableRow);
				editableRow = null;
			}
		}

		public void cancelEdit() {
			if (editableRow != null)
				try {
					server.getAllProducts().set(editableRow.intValue(), oldRowItem);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
		}

		@Override
		public int getRowCount() {
			try {
				return server.getAllProducts().size();
			} catch (RemoteException e) {
				e.printStackTrace();
				return 0;
			}
		}

		@Override
		public Object getValueAt(int row, int col) {
			try {
				Item item;

				item = server.getAllProducts().get(row);

				String result = "Error";
				switch (col) {
				case 0:
					result = "" + item.getId();
					break;
				case 1:
					result = item.getName();
					break;
				case 2:
					result = item.getVendor();
					break;
				case 3:
					result = "" + item.getPrice();
					break;
				case 4:
					result = item.getCategory();
					break;
				}
				return result;
			} catch (RemoteException e) {
				e.printStackTrace();
				return "Error";
			}
		}

		@Override
		public String getColumnName(int column) {
			return Item.FIELD_NAMES[column];
		}

		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return (Integer) rowIndex == editableRow && columnIndex > 0;
		}

		@Override
		public void setValueAt(Object val, int rowIndex, int columnIndex) {
			try {
				if (editableRow != null) {
					Item item;

					item = server.getAllProducts().get(editableRow.intValue());

					switch (columnIndex) {
					case 1:
						item.setName((String) val);
						break;
					case 2:
						item.setVendor((String) val);
						break;
					case 3:
						item.setPrice(Double.parseDouble((String) val));
						break;
					case 4:
						item.setCategory((String) val);
						break;
					}
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}

	}

}
