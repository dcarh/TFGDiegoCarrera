package modelClasses.dummies.objects

import modelClasses.app.social.Entry
import modelClasses.app.Time
import modelClasses.dummies.ids.{EntryIdDummies, RatingIdDummies, ReviewIdDummies, UserIdDummies}
import modelClasses.ids.Media.*

import java.time.{LocalDate, LocalDateTime}

object EntryDummies {
  val entry1: Entry = Entry(
    // The Bear
    EntryIdDummies.entryId1,
    UserIdDummies.userId5,
    TVShowId(136315),
    Some(RatingIdDummies.ratingId1),
    Some(ReviewIdDummies.reviewId1),
    true,
    true,
    Some(false),
    false,
    false,
    Some(LocalDate.of(2024, 11, 10)),
    Some(LocalDate.of(2024, 12, 2)),
    None,
    Some(Time(0, 13, 0)),
    List("disney", "trauma"),
    LocalDateTime.of(2024, 12, 2, 20, 16, 41)
  )

  val entry2: Entry = Entry(
    // Sifu
    EntryIdDummies.entryId2,
    UserIdDummies.userId3,
    VideogameId(144022),
    Some(RatingIdDummies.ratingId2),
    Some(ReviewIdDummies.reviewId2),
    true,
    true,
    Some(false),
    false,
    false,
    Some(LocalDate.of(2024, 8, 29)),
    Some(LocalDate.of(2024, 9, 5)),
    Some(6),
    Some(Time(0, 12, 12)),
    List(),
    LocalDateTime.of(2024, 9, 6, 10, 45, 24)
  )

  val entry3: Entry = Entry(
    // Death Stranding
    EntryIdDummies.entryId3,
    UserIdDummies.userId4,
    VideogameId(19564),
    Some(RatingIdDummies.ratingId3),
    Some(ReviewIdDummies.reviewId3),
    false,
    false,
    Some(false),
    true,
    false,
    Some(LocalDate.of(2024, 9, 13)),
    None,
    Some(48),
    Some(Time(0, 20, 43)),
    List("walking simulator", "kojima"),
    LocalDateTime.of(2024, 10, 22, 16, 7, 58)
  )

  val entry4: Entry = Entry(
    // Sound Of Metal
    EntryIdDummies.entryId4,
    UserIdDummies.userId1,
    MovieId(502033),
    Some(RatingIdDummies.ratingId4),
    Some(ReviewIdDummies.reviewId4),
    false,
    true,
    None,
    false,
    true,
    None,
    Some(LocalDate.of(2024, 11, 2)),
    None,
    Some(Time(0, 2, 0)),
    List(),
    LocalDateTime.of(2024, 11, 2, 23, 51, 20)
  )

  val entry5: Entry = Entry(
    // Requiem por un campesino español
    EntryIdDummies.entryId5,
    UserIdDummies.userId2,
    BookId("UU-VAAAACAAJ"),
    Some(RatingIdDummies.ratingId5),
    Some(ReviewIdDummies.reviewId5),
    true,
    true,
    Some(false),
    false,
    false,
    Some(LocalDate.of(2024, 8, 12)),
    Some(LocalDate.of(2024, 8, 13)),
    None,
    Some(Time(0, 4, 53)),
    List(),
    LocalDateTime.of(2024, 8, 14, 12, 7, 38)
  )

  val entry6: Entry = Entry(
    // Resident Evil 6
    EntryIdDummies.entryId6,
    UserIdDummies.userId2,
    VideogameId(1082),
    Some(RatingIdDummies.ratingId6),
    None,
    false,
    false,
    Some(false),
    true,
    false,
    Some(LocalDate.of(2024, 12, 28)),
    Some(LocalDate.of(2025, 1, 4)),
    Some(12),
    Some(Time(0, 13, 0)),
    List("survival horror", "RE"),
    LocalDateTime.of(2025, 1, 4, 23, 2, 36)
  )

  val entry7: Entry = Entry(
    // Videodrome
    EntryIdDummies.entryId7,
    UserIdDummies.userId3,
    MovieId(837),
    Some(RatingIdDummies.ratingId7),
    None,
    false,
    true,
    None,
    false,
    true,
    None,
    Some(LocalDate.of(2024, 9, 21)),
    None,
    Some(Time(0, 1, 28)),
    List(),
    LocalDateTime.of(2024, 9, 21, 18, 16, 3)
  )

  val entry8: Entry = Entry(
    // Eighty-six
    EntryIdDummies.entryId8,
    UserIdDummies.userId5,
    TVShowId(100565),
    Some(RatingIdDummies.ratingId8),
    None,
    false,
    true,
    Some(false),
    false,
    false,
    Some(LocalDate.of(2024, 10, 21)),
    Some(LocalDate.of(2024, 10, 31)),
    None,
    Some(Time(0, 0, 9)),
    List("anime", "mecha"),
    LocalDateTime.of(2024, 10, 31, 19, 42, 10)
  )

  val entry9: Entry = Entry(
    // Children of the sun
    EntryIdDummies.entryId9,
    UserIdDummies.userId2,
    VideogameId(284925),
    Some(RatingIdDummies.ratingId9),
    None,
    false,
    true,
    Some(false),
    false,
    true,
    Some(LocalDate.of(2024, 9, 2)),
    Some(LocalDate.of(2024, 9, 2)),
    Some(6),
    Some(Time(0, 4, 0)),
    List(),
    LocalDateTime.of(2024, 9, 2, 21, 15, 3)
  )

  val entry10: Entry = Entry(
    // El Loco (Kahlil Gibran)
    EntryIdDummies.entryId10,
    UserIdDummies.userId1,
    BookId("ZEfWEAAAQBAJ"),
    Some(RatingIdDummies.ratingId10),
    None,
    false,
    true,
    Some(false),
    false,
    false,
    Some(LocalDate.of(2024, 10, 19)),
    Some(LocalDate.of(2024, 10, 24)),
    None,
    Some(Time(0, 3, 15)),
    List(),
    LocalDateTime.of(2024, 10, 24, 13, 20, 39)
  )

  val entry11: Entry = Entry(
    // Beginners
    EntryIdDummies.entryId11,
    UserIdDummies.userId4,
    MovieId(55347),
    Some(RatingIdDummies.ratingId11),
    None,
    false,
    true,
    None,
    false,
    false,
    None,
    Some(LocalDate.of(24, 7, 28)),
    None,
    Some(Time(0, 1, 45)),
    List(),
    LocalDateTime.of(24, 8, 3, 19, 12, 9)
  )

  val entry12: Entry = Entry(
    // El coronel no tiene quien le escriba
    EntryIdDummies.entryId12,
    UserIdDummies.userId1,
    BookId("WLmqonsyH0QC"),
    None,
    None,
    false,
    false,
    Some(true),
    false,
    false,
    Some(LocalDate.of(2024, 7, 12)),
    Some(LocalDate.of(2024, 7, 19)),
    None,
    None,
    List(),
    LocalDateTime.of(2024, 7, 19, 17, 51, 24)
  )

  val entry13: Entry = Entry(
    // Hades
    EntryIdDummies.entryId13,
    UserIdDummies.userId4,
    VideogameId(113112),
    None,
    None,
    false,
    false,
    Some(true),
    false,
    false,
    Some(LocalDate.of(2024, 4, 17)),
    Some(LocalDate.of(2024, 9, 14)),
    Some(130),
    None,
    List("roguelike", "greek mythology"),
    LocalDateTime.of(2024, 9, 14, 23, 56, 12)
  )

  val entry14: Entry = Entry(
    // The Bear 07x02
    EntryIdDummies.entryId14,
    UserIdDummies.userId5,
    (TVShowId(136315), SeasonNumber(2), EpisodeNumber(7)),
    None,
    None,
    true,
    true,
    None,
    false,
    false,
    None,
    Some(LocalDate.of(2024, 11, 25)),
    None,
    Some(Time(0, 0, 30)),
    List(),
    LocalDateTime.of(2024, 11, 25, 18, 15, 49)
  )

  val entry15: Entry = Entry(
    // Westworld S.01
    EntryIdDummies.entryId15,
    UserIdDummies.userId1,
    (TVShowId(63247), SeasonNumber(1)),
    Some(RatingIdDummies.ratingId12),
    None,
    true,
    true,
    Some(false),
    false,
    false,
    Some(LocalDate.of(2024, 7, 3)),
    Some(LocalDate.of(2024, 7, 6)),
    None,
    Some(Time(0, 10, 0)),
    List(),
    LocalDateTime.of(2024, 7, 6, 14, 26, 8)
  )

}