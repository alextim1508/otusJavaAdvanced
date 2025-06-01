Реализровать класс для хранения данных в off-heap, используя инструменты библиотеки Java NIO:ByteBuffer и MappedByteBuffer
Данные списываются с файла и записываются в off-heap хранилище.
Пользователь может задавать размер off heap хранилища и вводить название файла. Если файл не найден, то бросается исключение.

**Как воспроизвести работу приложения:**

После клонирования проекта, выполнить команду _mvn package_ , перейти в директорию, в которую сбилделся jar файл,
и затем попустить приложение с помощью комады

`mvn package`

`java -jar ./target/task11NIO.jar`

Появится сообщение:
`Select type of cache: 1 - ByteBufferFileCache, 2 - MappedByteBufferFileCache`

Выбрать тип кеша, набрал соответствующую цифру

Появится меню:

            Select action:
                1) Get file
                2) Load file
                3) Quit

Загрузить файл в кеш, набрав число 2 и ввести полный путь к файлу

_Write file name:_

.\task11NIO\cachedFiles\1.txt

Появится подтвеждающее сообщение:

.\timofeev\task11NIO\cachedFiles\1.txt added to cache

Вывести содержимое файла из кэша, набрав 1 и ввести полный путь к файлу

_Write file name:_

.\task11NIO\cachedFiles\1.txt

_Content file:_

11