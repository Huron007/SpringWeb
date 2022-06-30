package com.crud.tasks.trello.facade;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.mapper.TrelloMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TrelloFacadeTestSuite {

    @Autowired
    private TrelloMapper trelloMapper;

    @Autowired
    private TaskMapper taskMapper;

    @Test
    public void trelloMapperTest(){
        //Given
        TrelloCard card = new TrelloCard("Test", "Test", "Test", "Test");
        TrelloList list = new TrelloList("Test", "Test", false);
        TrelloBoard board = new TrelloBoard("Test", "Test", new ArrayList<>());
        board.getLists().add(list);
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(list);
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(board);
        TrelloCardDto cardDto = new TrelloCardDto("Test", "Test", "Test", "Test");
        TrelloListDto listDto = new TrelloListDto("Test", "Test", false);
        TrelloBoardDto boardDto = new TrelloBoardDto("Test", "Test", new ArrayList<>());
        boardDto.getLists().add(listDto);
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        trelloListDtos.add(listDto);
        List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>();
        trelloBoardDtos.add(boardDto);
        //When
        TrelloCard mappedCard = trelloMapper.mapToCard(cardDto);
        TrelloCardDto mappedCardDto = trelloMapper.mapToCardDto(card);
        List<TrelloList> mappedTrelloList = trelloMapper.mapToList(trelloListDtos);
        List<TrelloListDto> mappedTrelloListDto = trelloMapper.mapToListDto(trelloLists);
        List<TrelloBoard> mappedTrelloBoard = trelloMapper.mapToBoards(trelloBoardDtos);
        List<TrelloBoardDto> mappedTrelloBoardDto = trelloMapper.mapToBoardsDto(trelloBoards);
        //Then
        assertEquals(card, mappedCard);
        assertEquals(cardDto, mappedCardDto);
        assertEquals(trelloLists, mappedTrelloList);
        assertEquals(trelloListDtos, mappedTrelloListDto);
        assertEquals(trelloBoards, mappedTrelloBoard);
        assertEquals(trelloBoardDtos, mappedTrelloBoardDto);
    }

    @Test
    public void taskMapperTest(){
        //Given
        Task task = new Task(1L, "Test", "Test");
        TaskDto taskDto = new TaskDto(1L, "Test", "Test");
        List<Task> taskList = new ArrayList<>();
        taskList.add(task);
        List<TaskDto> taskDtoList = new ArrayList<>();
        taskDtoList.add(taskDto);
        //When
        Task mappedTask = taskMapper.mapToTask(taskDto);
        TaskDto mappedTaskDto = taskMapper.mapToTaskDto(task);
        List<TaskDto> mappedTaskDtoList = taskMapper.mapToTaskDtoList(taskList);
        //Then
        assertEquals(task, mappedTask);
        assertEquals(taskDto, mappedTaskDto);
        assertEquals(taskDtoList, mappedTaskDtoList);
    }
}
