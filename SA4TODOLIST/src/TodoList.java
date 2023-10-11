import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;

public class TodoList extends JFrame {
    private JPanel mainPanel;
    private JTextField taskInputField;
    private JButton addButton;
    private JList<String> taskList;
    private DefaultListModel<String> listModel;
    private JButton deleteButton;
    private JButton markDoneButton;
    private JComboBox<String> filterComboBox;
    private JButton clearCompletedButton;
    private List<Task> tasks;

    // Construtor da classe TodoList
    public TodoList() {
        // Configura a janela principal
        super("To-Do List App");
        // Impede o fechamento padrão da janela
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        // Define o tamanho da janela
        this.setSize(500, 300);

        // Inicialização do painel principal
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Inicialização da lista de tarefas e do modelo da lista
        tasks = new ArrayList<>();
        listModel = new DefaultListModel<>();
        taskList = new JList<>(listModel);

        // Inicialização dos campos de entrada, botões e ComboBox
        taskInputField = new JTextField();
        addButton = new JButton("Adicionar");
        deleteButton = new JButton("Excluir");
        markDoneButton = new JButton("Concluir");
        filterComboBox = new JComboBox<>(new String[]{"Todas", "Ativas", "Concluídas"});
        clearCompletedButton = new JButton("Limpar Concluídas");

        // Configuração do painel de entrada
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(taskInputField, BorderLayout.CENTER);
        inputPanel.add(addButton, BorderLayout.EAST);

        // Configuração do painel de botões
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(deleteButton);
        buttonPanel.add(markDoneButton);
        buttonPanel.add(filterComboBox);
        buttonPanel.add(clearCompletedButton);

        // Adiciona os componentes ao painel principal
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(taskList), BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Adiciona o painel principal à janela
        this.add(mainPanel);

        // Adiciona listeners aos botões
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTask();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Quando o botão "Excluir" é pressionado
                int selectedIndex = taskList.getSelectedIndex();
                if (selectedIndex >= 0 && selectedIndex < tasks.size()) {
                    // Exibe uma caixa de diálogo de confirmação
                    int option = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir?", "Confirmação", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        // Remove a tarefa da lista e atualiza a interface
                        tasks.remove(selectedIndex);
                        updateTaskList();
                    }
                }
            }
        });

        markDoneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Quando o botão "Concluir" é pressionado
                markTaskDone();
            }
        });

        filterComboBox.addItemListener(e->{
                // Quando a seleção do ComboBox é alterada
                filterTasks();
            
        });

        clearCompletedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Quando o botão "Limpar Concluídas" é pressionado
                clearCompletedTasks();
            }
        });

        // Adiciona um KeyListener para a lista de tarefas para excluir com a tecla "DEL"
        taskList.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DELETE) {
                    // Quando a tecla "DEL" é pressionada na lista
                    int selectedIndex = taskList.getSelectedIndex();
                    if (selectedIndex >= 0 && selectedIndex < tasks.size()) {
                        // Exibe uma caixa de diálogo de confirmação
                        int option = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir?", "Confirmação", JOptionPane.YES_NO_OPTION);
                        if (option == JOptionPane.YES_OPTION) {
                            // Remove a tarefa da lista e atualiza a interface
                            deleteTask();
                        }
                    }
                }
            }
        });

        // Adiciona um WindowListener para confirmar o fechamento da aplicação
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                // Quando a janela está prestes a ser fechada
                int option = JOptionPane.showConfirmDialog(null, "Deseja realmente fechar a aplicação?", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    // Fecha a aplicação
                    System.exit(0);
                }
            }
        });
        run();
    }

    // Função para adicionar uma nova tarefa à lista
    private void addTask() {
        // Obtém a descrição da tarefa do campo de entrada
        String taskDescription = taskInputField.getText().trim();
        if (!taskDescription.isEmpty()) {
            // Cria uma nova tarefa, adiciona à lista e atualiza a interface
            Task newTask = new Task(taskDescription);
            tasks.add(newTask);
            updateTaskList();
            // Limpa o campo de entrada
            taskInputField.setText("");
        }
    }

    // Função para excluir a tarefa selecionada da lista
    private void deleteTask() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex >= 0 && selectedIndex < tasks.size()) {
            // Remove a tarefa da lista e atualiza a interface
            tasks.remove(selectedIndex);
            updateTaskList();
        }
    }

    // Função para marcar a tarefa selecionada como concluída
    private void markTaskDone() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex >= 0 && selectedIndex < tasks.size()) {
            // Marca a tarefa como concluída e atualiza a interface
            Task task = tasks.get(selectedIndex);
            task.setDone(true);
            updateTaskList();
        }
    }

    // Função para filtrar as tarefas com base na seleção do ComboBox
    private void filterTasks() {
        // Obtém a seleção do ComboBox
        String filter = (String) filterComboBox.getSelectedItem();
        // Limpa o modelo da lista
        listModel.clear();
        // Adiciona as tarefas filtradas ao modelo
        for (Task task : tasks) {
            if (filter.equals("Todas") || (filter.equals("Ativas") && !task.isDone()) || (filter.equals("Concluídas") && task.isDone())) {
                listModel.addElement(task.getDescription());
            }
        }
    }

    // Função para limpar todas as tarefas concluídas da lista
    private void clearCompletedTasks() {
        // Cria uma lista de tarefas concluídas
        List<Task> completedTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.isDone()) {
                completedTasks.add(task);
            }
        }
        // Remove as tarefas concluídas da lista e atualiza a interface
        tasks.removeAll(completedTasks);
        updateTaskList();
    }

    // Função para atualizar a lista de tarefas na interface gráfica
    private void updateTaskList() {
        // Limpa o modelo da lista
        listModel.clear();
        // Adiciona as tarefas atualizadas ao modelo
        for (Task task : tasks) {
            listModel.addElement(task.getDescription() + (task.isDone() ? " (Concluída)" : ""));
        }
    }

    // Função para exibir a janela principal
    public void run() {
        this.setVisible(true);
    }

}
