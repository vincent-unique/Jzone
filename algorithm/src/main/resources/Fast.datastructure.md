### 性能上比较优秀的数据结构


####检索速度上较优势的数据结构
```$
   一些优秀的数据结构经常被企业级中间件利用和实现，并优化。
```
#####1、平衡二叉树（二叉排序树）（相对平衡：红黑树）


#####2、B 树（B-、B+、B*）


#####3、跳跃表（SkipedList）- O (log n)
```$
    让链表的查询接近线性时间。
    传统链表的查询的时间复杂度为 O(n) ;由于其结构的限制，即时当前链表是有序的，也无法通过二分查找方式降低时间复杂度。
    
    @Reference ： https://www.cnblogs.com/acfox/p/3688607.html
```
```$
    JDK源码：ConcurrentSkipListMap、ConcurrentSkipListSet
```

#####4、Hash-散列表
```$
    一般，查找、命中算法优化的问题，可以从设计特殊hash方法的散列存储入手。
    如：从不重复的序列中命中一个唯一和值的长度一定的子序列（子序列唯一）
    例，从元素不重复的整形数组中查找一个和值为10的长度为2的子序列的index；
    可以考虑设计hash ：
        hash( value, targetSum){
            if(value <= targetSum / 2) {
                return  value;
            }else{
                return targetSum - value;
            }
        }
        
```