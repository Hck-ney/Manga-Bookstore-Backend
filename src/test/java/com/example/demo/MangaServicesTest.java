//package com.example.demo;
//
//import com.example.demo.enums.Availability;
//import com.example.demo.enums.Category;
//import com.example.demo.manga.dto.MangaRequest;
//import com.example.demo.manga.dto.MangaResponse;
//import com.example.demo.manga.entity.Manga;
//import com.example.demo.manga.repository.MangaRepository;
//import com.example.demo.manga.services.MangaServices;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import java.math.BigDecimal;
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class MangaServicesTest {
//
//    @Mock
//    private MangaRepository mangaRepository;
//
//    @InjectMocks
//    private MangaServices mangaServices;
//
//    private MangaRequest validRequest;
//    private MangaRequest invalidRequest;
//
//    @BeforeEach
//    void testManga() {
//        validRequest = new MangaRequest(
//                1L,
//                "One Piece",
//                "Eiichiro Oda",
//                BigDecimal.valueOf(450),
//                1997,
//                "http://example.com/cover.jpg",
//                Category.SHONEN,
//                Availability.AVAILABLE,
//                "A boy dreams of becoming the Pirate King."
//        );
//        invalidRequest = new MangaRequest(
//                1L,
//                "One Piece",
//                "Eiichiro Oda",
//                BigDecimal.valueOf(450),
//                1997,
//                "http://example.com/cover.jpg",
//                Category.SHONEN,
//                Availability.AVAILABLE,
//                "zxc"
//        );
//    }
//    @Test
//    void shouldCreateMangaSuccessfully() {
//        when(mangaRepository.save(any(Manga.class))).thenAnswer(inv -> inv.getArgument(0));
//
//        MangaResponse response = mangaServices.createManga(validRequest);
//
//        assertThat(response).isNotNull();
//        verify(mangaRepository, times(1)).save(any(Manga.class));
//    }
//
//    @Test
//    void shouldThrowErrorCreatingMangaWithInvalidResponse() {
//        when(mangaRepository.save(any(Manga.class))).thenAnswer(inv -> inv.getArgument(0));
//        MangaResponse response = mangaServices.createManga(invalidRequest);
//        assertThat(response).isNull();
//        verify(mangaRepository, times(1)).save(any(Manga.class));
//    }
//}
