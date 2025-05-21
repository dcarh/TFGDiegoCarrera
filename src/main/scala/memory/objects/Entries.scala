package memory.objects

import memory.ids.{EntryIds, RatingIds, ReviewIds, UserIds}
import modelClasses.app.social.Entry
import modelClasses.ids.Media.*

import java.time.{LocalDate, LocalDateTime}

object Entries {
  val entry1: Entry = Entry(
    // The Bear
    EntryIds.entryId1,
    UserIds.userId5,
    TvShowId(136315),
    Some(RatingIds.ratingId1),
    Some(ReviewIds.reviewId1),
    true,
    Some(false),
    Some(false),
    false,
    false,
    Some(LocalDate.of(2024, 11, 10)),
    Some(LocalDate.of(2024, 12, 2)),
    None,
    LocalDateTime.of(2024, 12, 2, 20, 16, 41)
  )

  val entry2: Entry = Entry(
    // Sifu
    EntryIds.entryId2,
    UserIds.userId3,
    VideogameId(144022),
    Some(RatingIds.ratingId2),
    Some(ReviewIds.reviewId2),
    true,
    Some(false),
    Some(false),
    false,
    false,
    Some(LocalDate.of(2024, 8, 29)),
    Some(LocalDate.of(2024, 9, 5)),
    Some(6),
    LocalDateTime.of(2024, 9, 6, 10, 45, 24)
  )

  val entry3: Entry = Entry(
    // Death Stranding
    EntryIds.entryId3,
    UserIds.userId4,
    VideogameId(19564),
    Some(RatingIds.ratingId3),
    Some(ReviewIds.reviewId3),
    false,
    Some(false),
    Some(false),
    true,
    false,
    Some(LocalDate.of(2024, 9, 13)),
    None,
    Some(48),
    LocalDateTime.of(2024, 10, 22, 16, 7, 58)
  )

  val entry4: Entry = Entry(
    // Sound Of Metal
    EntryIds.entryId4,
    UserIds.userId1,
    MovieId(502033),
    Some(RatingIds.ratingId4),
    Some(ReviewIds.reviewId4),
    true,
    None,
    None,
    false,
    true,
    None,
    Some(LocalDate.of(2024, 11, 2)),
    None,
    LocalDateTime.of(2024, 11, 2, 23, 51, 20)
  )

  val entry5: Entry = Entry(
    // Requiem por un campesino español
    EntryIds.entryId5,
    UserIds.userId2,
    BookId("UU-VAAAACAAJ"),
    Some(RatingIds.ratingId5),
    Some(ReviewIds.reviewId5),
    true,
    Some(false),
    Some(false),
    false,
    false,
    Some(LocalDate.of(2024, 8, 12)),
    Some(LocalDate.of(2024, 8, 13)),
    None,
    LocalDateTime.of(2024, 8, 14, 12, 7, 38)
  )

  val entry6: Entry = Entry(
    // Resident Evil 6
    EntryIds.entryId6,
    UserIds.userId2,
    VideogameId(1082),
    Some(RatingIds.ratingId6),
    None,
    false,
    Some(false),
    Some(false),
    true,
    false,
    Some(LocalDate.of(2024, 12, 28)),
    Some(LocalDate.of(2025, 1, 4)),
    Some(12),
    LocalDateTime.of(2025, 1, 4, 23, 2, 36)
  )

  val entry7: Entry = Entry(
    // Videodrome
    EntryIds.entryId7,
    UserIds.userId3,
    MovieId(837),
    Some(RatingIds.ratingId7),
    None,
    true,
    None,
    None,
    false,
    true,
    None,
    Some(LocalDate.of(2024, 9, 21)),
    None,
    LocalDateTime.of(2024, 9, 21, 18, 16, 3)
  )

  val entry8: Entry = Entry(
    // Eighty-six
    EntryIds.entryId8,
    UserIds.userId5,
    TvShowId(100565),
    Some(RatingIds.ratingId8),
    None,
    true,
    Some(false),
    Some(false),
    false,
    false,
    Some(LocalDate.of(2024, 10, 21)),
    Some(LocalDate.of(2024, 10, 31)),
    None,
    LocalDateTime.of(2024, 10, 31, 19, 42, 10)
  )

  val entry9: Entry = Entry(
    // Children of the sun
    EntryIds.entryId9,
    UserIds.userId2,
    VideogameId(284925),
    Some(RatingIds.ratingId9),
    None,
    true,
    Some(false),
    Some(false),
    false,
    true,
    Some(LocalDate.of(2024, 9, 2)),
    Some(LocalDate.of(2024, 9, 2)),
    Some(6),
    LocalDateTime.of(2024, 9, 2, 21, 15, 3)
  )

  val entry10: Entry = Entry(
    // El Loco (Kahlil Gibran)
    EntryIds.entryId10,
    UserIds.userId1,
    BookId("ZEfWEAAAQBAJ"),
    Some(RatingIds.ratingId10),
    None,
    true,
    Some(false),
    Some(false),
    false,
    false,
    Some(LocalDate.of(2024, 10, 19)),
    Some(LocalDate.of(2024, 10, 24)),
    None,
    LocalDateTime.of(2024, 10, 24, 13, 20, 39)
  )

  val entry11: Entry = Entry(
    // Beginners
    EntryIds.entryId11,
    UserIds.userId4,
    MovieId(55347),
    Some(RatingIds.ratingId11),
    None,
    true,
    None,
    None,
    false,
    false,
    None,
    Some(LocalDate.of(24, 7, 28)),
    None,
    LocalDateTime.of(24, 8, 3, 19, 12, 9)
  )

  val entry12: Entry = Entry(
    // El coronel no tiene quien le escriba
    EntryIds.entryId12,
    UserIds.userId1,
    BookId("WLmqonsyH0QC"),
    None,
    None,
    false,
    Some(false),
    Some(true),
    false,
    false,
    Some(LocalDate.of(2024, 7, 12)),
    Some(LocalDate.of(2024, 7, 19)),
    None,
    LocalDateTime.of(2024, 7, 19, 17, 51, 24)
  )

  val entry13: Entry = Entry(
    // Hades
    EntryIds.entryId13,
    UserIds.userId4,
    VideogameId(113112),
    None,
    None,
    false,
    Some(false),
    Some(true),
    false,
    false,
    Some(LocalDate.of(2024, 4, 17)),
    Some(LocalDate.of(2024, 9, 14)),
    Some(130),
    LocalDateTime.of(2024, 9, 14, 23, 56, 12)
  )

  val entry14: Entry = Entry(
    // The Bear 07x02
    EntryIds.entryId14,
    UserIds.userId5,
    (TvShowId(136315), TvSeasonNumber(2), TvEpisodeNumber(7)),
    None,
    None,
    true,
    None,
    None,
    false,
    false,
    None,
    Some(LocalDate.of(2024, 11, 25)),
    None,
    LocalDateTime.of(2024, 11, 25, 18, 15, 49)
  )

  val entry15: Entry = Entry(
    // Westworld S.01
    EntryIds.entryId15,
    UserIds.userId1,
    (TvShowId(63247), TvSeasonNumber(1)),
    Some(RatingIds.ratingId12),
    None,
    true,
    Some(false),
    Some(false),
    false,
    false,
    Some(LocalDate.of(2024, 7, 3)),
    Some(LocalDate.of(2024, 7, 6)),
    None,
    LocalDateTime.of(2024, 7, 6, 14, 26, 8)
  )

  val entry16: Entry = Entry(
    // Death Stranding
    EntryIds.entryId16,
    UserIds.userId5,
    VideogameId(19564),
    Some(RatingIds.ratingId13),
    None,
    true,
    Some(false),
    Some(false),
    false,
    false,
    Some(LocalDate.of(2025, 4, 4)),
    Some(LocalDate.of(2025, 5, 18)),
    Some(48),
    LocalDateTime.of(2025, 5, 18, 20, 19, 18)
  )

  val entry17: Entry = Entry(
    // Death Stranding
    EntryIds.entryId17,
    UserIds.userId3,
    VideogameId(19564),
    None,
    None,
    true,
    Some(false),
    Some(false),
    false,
    false,
    Some(LocalDate.of(2024, 11, 30)),
    Some(LocalDate.of(2024, 12, 16)),
    Some(48),
    LocalDateTime.of(2024, 12, 16, 21, 50, 12)
  )

  val entry18: Entry = Entry(
    // El coronel no tiene quien le escriba
    EntryIds.entryId18,
    UserIds.userId2,
    BookId("WLmqonsyH0QC"),
    Some(RatingIds.ratingId14),
    None,
    true,
    Some(false),
    Some(false),
    false,
    false,
    Some(LocalDate.of(2024, 8, 12)),
    Some(LocalDate.of(2024, 8, 21)),
    None,
    LocalDateTime.of(2024, 8, 21, 23, 9, 11)
  )

}