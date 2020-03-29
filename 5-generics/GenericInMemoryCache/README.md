# Generic In-Memory Cache :package:

Кеширането (caching) в паметта е често използван подход за подобряване на бързодействието на алгоритми, използващи интензивно данни, съхранявани на бавно устройство.
Ще създадем собствена имплементация на generic кеш (cache).

Достъпът до кеширани данни е в порядъци по-бърз, но за съжаление, кешът си има фиксиран капацитет, който не може да се надвишава. По тази причина, всеки кеш има т.нар. *eviction policy*, т.е. правило, по което се определя, кои елементи се изтриват от кеша при опит за добавяне на нов елемент при запълнен капацитет.

## Implementation

Ще създадем две имплементации на интерфейс `Cache`, различаващи се по това, какво *eviction policy* използват:

- [Random Replacement (RR)](https://en.wikipedia.org/wiki/Cache_replacement_policies#Random_replacement_(RR))
- [Least-Frequently Used (LFU)](https://en.wikipedia.org/wiki/Cache_replacement_policies#Least-frequently_used_(LFU))
