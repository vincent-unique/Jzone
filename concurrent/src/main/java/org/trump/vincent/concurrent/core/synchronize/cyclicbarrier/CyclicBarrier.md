#CyclicBarrier Insctruction
```
   CyclicBarrier is a concurrent synchronizer.
   CyclicBarrier can make some thread wait which applys CyclicBarrier await method , until the Barrier point (Barrier thread)
   finish its all tasks and ends itself. And then following works after CyclicBarrier await  will be on .
   
   Finally ,we should reset the CyclicBarrier. I.E. CyclicBarrier is a reusable synchonizer.
```

##CyclicBarrier Implementation
```markdown
    CyclicBarrie is implemented with ReentrantLock and Condtion.
    Moreover,the core method or function (await) calls Condition's method (await) which is implemented with LockSupport.
```