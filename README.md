# CampusTask – Pengelola Tugas Mahasiswa

CampusTask adalah aplikasi Android sederhana untuk membantu mahasiswa mengelola tugas perkuliahan, mencatat deadline, menentukan prioritas, dan memantau status pengerjaan tugas.

## Fitur Utama

- **Dashboard:** Menampilkan ringkasan tugas mahasiswa.
- **Tambah Tugas:** Menambahkan tugas baru dengan judul, mata kuliah, deskripsi, deadline, prioritas, dan status.
- **Daftar Tugas:** Melihat seluruh tugas yang tersimpan.
- **Detail Tugas:** Melihat informasi lengkap tugas.
- **Edit Tugas:** Mengubah informasi tugas yang sudah dibuat.
- **Hapus Tugas:** Menghapus tugas dengan dialog konfirmasi.
- **Filter Status:** Memantau status tugas.
- **Penyimpanan Lokal:** Data tugas disimpan menggunakan Room Database.

## Teknologi yang Digunakan

- Kotlin
- Jetpack Compose
- Material 3
- Android Navigation
- ViewModel
- StateFlow
- Room Database
- Kotlin Coroutines

## Arsitektur Aplikasi

CampusTask menggunakan pemisahan komponen untuk mengelola data dan UI:

- **UI:** Jetpack Compose untuk membangun antarmuka.
- **ViewModel:** Mengelola state dan interaksi UI.
- **Repository:** Menghubungkan ViewModel dengan DAO.
- **Room Database:** Menyimpan data tugas secara lokal.

## Struktur Project

```text
com.example.campustask
├── data
│   ├── Task.kt
│   ├── TaskDao.kt
│   ├── TaskDatabase.kt
│   └── TaskRepository.kt
├── navigation
│   └── AppNavigation.kt
├── ui
│   └── screens
│       ├── DashboardScreen.kt
│       ├── TaskListScreen.kt
│       ├── AddEditTaskScreen.kt
│       └── TaskDetailScreen.kt
└── viewmodel
    ├── TaskViewModel.kt
    ├── TaskViewModelFactory.kt
    └── TaskViewModelProvider.kt
```

## Tujuan Pengembangan

Aplikasi ini dikembangkan sebagai proyek UAS Mobile Programming untuk menerapkan pemrograman Android menggunakan Kotlin, Jetpack Compose, Navigation, ViewModel, dan penyimpanan data lokal.

## Pengembangan

Aplikasi ini masih dalam tahap pengembangan dan dapat ditingkatkan dengan penambahan fitur serta penyempurnaan UI/UX.