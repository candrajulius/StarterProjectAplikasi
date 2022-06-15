package com.candra.starterprojectaplikasi.core.di

import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class AppScope

/*
Kesimpulan
Karena alasan di atas Anda harus memiliki scope dengan tingkat yang lebih tinggi, caranya yaitu dengan membuat custom scope.

Mengapa kedua component tersebut tidak boleh memiliki scope yang sama? Hal ini karena kedua component tidak memiliki lifecycle yang sama. Bisa jadi CoreComponent memiliki lifecycle yang lebih panjang karena ia adalah bagian dari AppComponent. Dan ingat bahwa CoreComponent tidak mungkin ada kecuali AppComponent ada, namun tidak sebaliknya. Karena itulah mereka harus memiliki lifecycle yang berbeda.
 */