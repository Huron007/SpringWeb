package com.crud.tasks.trello.facade;

import com.crud.tasks.domain.*;
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
}
