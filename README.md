# TechPropProc

TechProg - программа, обеспечивающая парсинг мудростей 3 типов - Афоризмы, Пословицы и Загадки.
Прочитанные в удобном для хранения данные преобразовываются в удобный для человека формат.

Proc - вариант программы на основе процедурного подхода программирования

## Использование

Используйте java интерпретатор 18+ версии

```bash
java -jar TechMethProgProc-1.0-jar-with-dependencies.jar -i /some/input/file -o /some/output/file
```

### Аргументы:

- -i, --input - Путь к входному файлу
- -o, --output - Путь к выходному файлу
- -s, --sort (Опционально) - Отсортировать прочитанные мудрости
- -p, --pairs (Опционально) - Добавить к выведенной информации типы попарно перебранных мудростей
- -f, --first-type (Опционально) - Добавить к выведенной информации список из элементов первого типа (афоризмов)
- -v, --verbose (Опционально) - Выводить в консоль процесс работы программы
### Входные данные:

#### Ограничения:

- Каждая мудрость содержит 3 поля + 1 поле для определения её типа
- Все 4 поля идут непрерывно, строка за строкой
- Между мудростями (т.е. между 4 идущими подряд строками) допускаются пустые строки

#### Порядок строк мудрости:

1) Тип мудрости - целое число от 1 до 3 (1 - афоризм, 2 - пословица, 3 - загадка)
2) Дополнительный аргумент, зависящий от типа мудрости - строка из как минимум 1 символа
3) Текст мудрости - строка из как минимум 1 символа
4) Субъективная оценка мудрости - число от 1 до 10
