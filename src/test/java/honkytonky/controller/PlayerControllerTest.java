package honkytonky.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import honkytonky.factories.RoomFactory;
import honkytonky.objects.Door;
import honkytonky.objects.Player;
import honkytonky.objects.Room;
import java.io.ByteArrayInputStream;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class PlayerControllerTest {

    @InjectMocks
    PlayerController pc;

    @Mock
    Player player;

    @Mock
    RoomFactory rf;

    @Mock
    Room room;

    @Mock
    Door door;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testMove() {

        ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(in);

        List<Door> doors = Collections.singletonList(door);

        when(player.getCurrentRoom()).thenReturn(room);
        when(player.getCurrentRoom().getDoors()).thenReturn(doors);
        when(door.getTargetRoom()).thenReturn(room);
        when(door.getTargetRoom().getName()).thenReturn("targetRoom");
        when(rf.getRoomByName(Mockito.anyString())).thenReturn(room);

        pc.move(scanner, rf);

        verify(player, times(3)).getCurrentRoom();
        verify(player, times(1)).setCurrentRoom(any());
        verify(player.getCurrentRoom(), times(1)).listDoorOptions();
        verify(player.getCurrentRoom(), times(1)).getDoors();
        verify(door, times(2)).getTargetRoom();
        verify(door.getTargetRoom(), times(1)).getName();
        verify(rf, times(1)).getRoomByName(anyString());
        verify(player, never()).getInventory();
        verify(player, never()).getExperience();
    }
}