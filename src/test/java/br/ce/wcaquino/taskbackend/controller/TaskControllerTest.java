package br.ce.wcaquino.taskbackend.controller;

import br.ce.wcaquino.taskbackend.model.Task;
import br.ce.wcaquino.taskbackend.repo.TaskRepo;
import br.ce.wcaquino.taskbackend.utils.ValidationException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TaskControllerTest {

    @InjectMocks
    private TaskController taskController;

    @Mock
    private TaskRepo taskRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void deveSalvarTarefaComSucesso() throws ValidationException {
        Task task = new Task();
        task.setTask("Test task");
        task.setDueDate(LocalDate.now().plusDays(1));

        when(taskRepository.save(task)).thenReturn(task);
        ResponseEntity<Task> response = taskController.save(task);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(task, response.getBody());

    }


    @Test
    public void naoDeveSalvarTarefaSemDescricao() {
        Task task = new Task();
        task.setDueDate(LocalDate.now());
        try {
            taskController.save(task);
        } catch (ValidationException e) {
            assertEquals("Fiddll the task description", e.getMessage());
        }
    }

    @Test
    public void naoDeveSalvarTarefaSemData() {
        Task task = new Task();
        task.setTask("Teste");
        try {
            taskController.save(task);
        } catch (ValidationException e) {
            assertEquals("Fildafl the due date", e.getMessage());
        }
    }

    @Test
    public void naoDeveSalvarTarefaComDataPassada() {
        Task task = new Task();
        task.setTask("Teste");
        task.setDueDate(LocalDate.now().plusDays(-1));
        try {
            taskController.save(task);
        } catch (ValidationException e) {
            assertEquals("Dufdsae date must not be in past", e.getMessage());
        }
    }
}
