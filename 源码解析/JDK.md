ArrayList

```java
 private boolean batchRemove(Collection<?> c, boolean complement) {//retailAll取交集true;removeAll,false
        final Object[] elementData = this.elementData;//原数据
        int r = 0, w = 0;//读指针,写指针
        boolean modified = false;//是否修改
        try {
            for (; r < size; r++)//遍历读 
                if (c.contains(elementData[r]) == complement)//存在要移除的元素,移除
                    elementData[w++] = elementData[r];//
        } finally {
            // Preserve behavioral compatibility with AbstractCollection,
            // even if c.contains() throws.
            if (r != size) {//读未完成, 
                System.arraycopy(elementData, r,
                                 elementData, w,
                                 size - r);//System中提供了一个native静态方法arraycopy(),可以使用这个方法来实现数组之间的复制。对于一维数组来说，这种复制属性值传递，修改副本不会影响原来的值。对于二维或者一维数组中存放的是对象时，复制结果是一维的引用变量传递给副本的一维数组，修改副本时，会影响原来的数组。
                //不影响,浅复制 
                w += size - r;
            }
            if (w != size) {
                // clear to let GC do its work
                for (int i = w; i < size; i++)
                    elementData[i] = null;
                modCount += size - w;
                size = w;
                modified = true;
            }
        }
        return modified;
    }
```

c{2,3,4} e{1,2,3,4,5}

即retailAll时,r=0时,w=0,

​						r=1时,w=1,{2,2,3,4,5}

​						r=2时,w=2,{2,3,3,4,5}

​						r=3时,w=3,{2,3,4,4,5}

​						r=4时,w=3;

w!=size  ->{2,3,4,null,null}

removeAll时,r=0时,w=1,{1,2,3,4,5}

​					   r=1时,w=1,{1,2,3,4,5}

​						r=2时,w=1,{1,2,3,4,5}

​						r=3时,w=1,{1,2,3,4,5}

​						r=4时,w=2;{1,5,3,4,5}

w!=size  ->{1,5,null,null,null}



HashMap

```
jdk1.7




jdk1.8

public V put(K key, V value) {
    // hash(key) 计算哈希值
    return putVal(hash(key), key, value, false, true);
}

final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
               boolean evict) {
    Node<K,V>[] tab; // tables 数组
    Node<K,V> p; // 对应位置的 Node 节点
    int n; // 数组大小
    int i; // 对应的 table 的位置
    // <1> 如果 table 未初始化，或者容量为 0 ，则进行扩容
    if ((tab = table) == null || (n = tab.length) == 0)
        n = (tab = resize() /*扩容*/ ).length;
    // <2> 如果对应位置的 Node 节点为空，则直接创建 Node 节点即可。
    if ((p = tab[i = (n - 1) & hash] /*获得对应位置的 Node 节点*/) == null)
        tab[i] = newNode(hash, key, value, null);
    // <3> 如果对应位置的 Node 节点非空，则可能存在哈希冲突
    else {
        Node<K,V> e; // key 在 HashMap 对应的老节点
        K k;
        // <3.1> 如果找到的 p 节点，就是要找的，则则直接使用即可
        if (p.hash == hash && // 判断 hash 值相等
            ((k = p.key) == key || (key != null && key.equals(k)))) // 判断 key 真正相等
            e = p;
        // <3.2> 如果找到的 p 节点，是红黑树 Node 节点，则直接添加到树中
        else if (p instanceof TreeNode)
            e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
        // <3.3> 如果找到的 p 是 Node 节点，则说明是链表，需要遍历查找
        else {
            // 顺序遍历链表
            for (int binCount = 0; ; ++binCount) {
                // `(e = p.next)`：e 指向下一个节点，因为上面我们已经判断了最开始的 p 节点。
                // 如果已经遍历到链表的尾巴，则说明 key 在 HashMap 中不存在，则需要创建
                if ((e = p.next) == null) {
                    // 创建新的 Node 节点
                    p.next = newNode(hash, key, value, null);
                    // 链表的长度如果数量达到 TREEIFY_THRESHOLD（8）时，则进行树化。
                    if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                        treeifyBin(tab, hash);
                    break; // 结束
                }
                // 如果遍历的 e 节点，就是要找的，则则直接使用即可
                if (e.hash == hash &&
                    ((k = e.key) == key || (key != null && key.equals(k))))
                    break; // 结束
                // p 指向下一个节点
                p = e;
            }
        }
        // <4.1> 如果找到了对应的节点
        if (e != null) { // existing mapping for key
            V oldValue = e.value;
            // 修改节点的 value ，如果允许修改
            if (!onlyIfAbsent || oldValue == null)
                e.value = value;
            // 节点被访问的回调
            afterNodeAccess(e);
            // 返回老的值
            return oldValue;
        }
    }
    // <4.2>
    // 增加修改次数
    ++modCount;
    // 如果超过阀值，则进行扩容
    if (++size > threshold)
        resize();
    // 添加节点后的回调
    afterNodeInsertion(evict);
    // 返回 null
    return null;
}
```

ConcurrentHashMap

```

```

ReentrantLock  

```java
	/**RTL
     * Sync object for non-fair locks
     */
    static final class NonfairSync extends Sync {
        private static final long serialVersionUID = 7316153563782823691L;

        /**
         * Performs lock.  Try immediate barge, backing up to normal
         * acquire on failure.
         */
        final void lock() {
            if (compareAndSetState(0, 1))//期望值,更新值
                setExclusiveOwnerThread(Thread.currentThread());//自旋成功设为Owner线程
            else
                acquire(1);
        }

        protected final boolean tryAcquire(int acquires) {
            return nonfairTryAcquire(acquires);
        }
    }

	//AQS是Sync的父类

	 /**AQS
     * Atomically sets synchronization state to the given updated
     * value if the current state value equals the expected value.
     * This operation has memory semantics of a {@code volatile} read
     * and write.
     *
     * @param expect the expected value
     * @param update the new value
     * @return {@code true} if successful. False return indicates that the actual
     *         value was not equal to the expected value.
     */
    protected final boolean compareAndSetState(int expect, int update) {
        // See below for intrinsics setup to support this
        return unsafe.compareAndSwapInt(this, stateOffset, expect, update);
    }


	/**AQS
     * Acquires in exclusive mode, ignoring interrupts.  Implemented
     * by invoking at least once {@link #tryAcquire},
     * returning on success.  Otherwise the thread is queued, possibly
     * repeatedly blocking and unblocking, invoking {@link
     * #tryAcquire} until success.  This method can be used
     * to implement method {@link Lock#lock}.
     *
     * @param arg the acquire argument.  This value is conveyed to
     *        {@link #tryAcquire} but is otherwise uninterpreted and
     *        can represent anything you like.
     */
    public final void acquire(int arg) {
        if (!tryAcquire(arg) &&
            acquireQueued(addWaiter(Node.EXCLUSIVE), arg))
            //addWaiter(null)
            selfInterrupt();
    }

		//RTL

		/**
         * Performs non-fair tryLock.  tryAcquire is implemented in
         * subclasses, but both need nonfair try for trylock method.
         */
        final boolean nonfairTryAcquire(int acquires) {
            final Thread current = Thread.currentThread();
            int c = getState();
            if (c == 0) {//持有锁线程放开了,直接上锁成功
                if (compareAndSetState(0, acquires)) {
                    setExclusiveOwnerThread(current);
                    return true;
                }
            }
            else if (current == getExclusiveOwnerThread()) {//可重入
                int nextc = c + acquires;
                if (nextc < 0) // overflow
                    throw new Error("Maximum lock count exceeded");
                setState(nextc);
                return true;
            }
            return false;
        }


	//AQS
 	/**
     * Creates and enqueues node for current thread and given mode.
     *
     * @param mode Node.EXCLUSIVE for exclusive, Node.SHARED for shared
     * @return the new node
     */
    private Node addWaiter(Node mode) {
        Node node = new Node(Thread.currentThread(), mode);
        // Try the fast path of enq; backup to full enq on failure
        Node pred = tail;//尾指针节点
        if (pred != null) {
            node.prev = pred;//将当前线程入CLH
            
     /** <p>To enqueue into a CLH lock, you atomically splice it in as new
     * tail. To dequeue, you just set the head field.
     * <pre>
     *      +------+  prev +-----+       +-----+
     * head |      | <---- |     | <---- |     |  tail
     *      +------+       +-----+       +-----+
     * </pre>**/
            if (compareAndSetTail(pred, node)) {//期望tail时尾节点,将尾节点设置为当前线程节点,成功返回当前线程节点
                pred.next = node;
                return node;
            }
        }
        enq(node);//不需要此返回值
        return node;//返回当前线程节点
    }


	/**AQS
     * Inserts node into queue, initializing if necessary. See picture above.
     * @param node the node to insert
     * @return node's predecessor
     */
    private Node enq(final Node node) {
        for (;;) {
            Node t = tail;
            if (t == null) { // Must initialize 即队列不空,就算没线程节点也会有一个为null的节点 
                if (compareAndSetHead(new Node()))//期望null,替换head为null
                    tail = head;
            } else {//当前线程节点CAS入队
                node.prev = t;
                if (compareAndSetTail(t, node)) {
                    t.next = node;
                    return t;//没有取此值
                }
            }
        }
    }



	/*AQS
     * Various flavors of acquire, varying in exclusive/shared and
     * control modes.  Each is mostly the same, but annoyingly
     * different.  Only a little bit of factoring is possible due to
     * interactions of exception mechanics (including ensuring that we
     * cancel if tryAcquire throws exception) and other control, at
     * least not without hurting performance too much.
     */

    /**
     * Acquires in exclusive uninterruptible mode for thread already in
     * queue. Used by condition wait methods as well as acquire.
     *
     * @param node the node
     * @param arg the acquire argument
     * @return {@code true} if interrupted while waiting
     */
    final boolean acquireQueued(final Node node, int arg) {
        boolean failed = true;
        try {
            boolean interrupted = false;
            for (;;) {
                final Node p = node.predecessor();//当前节点前节点
                if (p == head && tryAcquire(arg)) {//如果是头节点,且tryAcquire(1),没有线程竞争直接将当前节点设置成头节点
                    setHead(node);
                    p.next = null; // help GC
                    failed = false;
                    return interrupted;
                }
                if (shouldParkAfterFailedAcquire(p, node) &&
                    parkAndCheckInterrupt())//阻塞当前线程
                    
                    
                    interrupted = true;
            }
        } finally {
            if (failed)
                cancelAcquire(node);
        }
    }


		//RTL
		/**
         * Performs non-fair tryLock.  tryAcquire is implemented in
         * subclasses, but both need nonfair try for trylock method.
         */
        final boolean nonfairTryAcquire(int acquires) {
            final Thread current = Thread.currentThread();
            int c = getState();
            if (c == 0) {
                if (compareAndSetState(0, acquires)) {
                    setExclusiveOwnerThread(current);
                    return true;
                }
            }
            else if (current == getExclusiveOwnerThread()) {
                int nextc = c + acquires;
                if (nextc < 0) // overflow
                    throw new Error("Maximum lock count exceeded");
                setState(nextc);
                return true;
            }
            return false;
        }


	//AQS
	/**
     * Checks and updates status for a node that failed to acquire.
     * Returns true if thread should block. This is the main signal
     * control in all acquire loops.  Requires that pred == node.prev.
     *
     * @param pred node's predecessor holding status
     * @param node the node
     * @return {@code true} if thread should block
     */
    private static boolean shouldParkAfterFailedAcquire(Node pred, Node node) {
        int ws = pred.waitStatus;
        if (ws == Node.SIGNAL)//需要被唤醒
            /*
             * This node has already set status asking a release
             * to signal it, so it can safely park.
             */
            return true;
        if (ws > 0) {//前面节点被取消,一直前移
            /*
             * Predecessor was cancelled. Skip over predecessors and
             * indicate retry.
             */
            do {
                node.prev = pred = pred.prev;
            } while (pred.waitStatus > 0);
            pred.next = node;
        } else {
            /*
             * waitStatus must be 0 or PROPAGATE.  Indicate that we
             * need a signal, but don't park yet.  Caller will need to
             * retry to make sure it cannot acquire before parking.
             */
            compareAndSetWaitStatus(pred, ws, Node.SIGNAL);//设置为被唤醒
        }
        return false;
    }
	/**----------------------Lock Finish----------------------*/



	/**AQS
     * Releases in exclusive mode.  Implemented by unblocking one or
     * more threads if {@link #tryRelease} returns true.
     * This method can be used to implement method {@link Lock#unlock}.
     *
     * @param arg the release argument.  This value is conveyed to
     *        {@link #tryRelease} but is otherwise uninterpreted and
     *        can represent anything you like.
     * @return the value returned from {@link #tryRelease}
     */
    public final boolean release(int arg) {
        if (tryRelease(arg)) {
            Node h = head;
            if (h != null && h.waitStatus != 0)
                unparkSuccessor(h);
            return true;
        }
        return false;
    }

		//RTL

        protected final boolean tryRelease(int releases) {
            int c = getState() - releases;
            if (Thread.currentThread() != getExclusiveOwnerThread())
                throw new IllegalMonitorStateException();
            boolean free = false;
            if (c == 0) {
                free = true;
                setExclusiveOwnerThread(null);
            }
            setState(c);
            return free;
        }


 	/**AQS
     * Wakes up node's successor, if one exists.
     *
     * @param node the node
     */
    private void unparkSuccessor(Node node) {
        /*
         * If status is negative (i.e., possibly needing signal) try
         * to clear in anticipation of signalling.  It is OK if this
         * fails or if status is changed by waiting thread.
         */
        int ws = node.waitStatus;
        if (ws < 0)
            compareAndSetWaitStatus(node, ws, 0);

        /*
         * Thread to unpark is held in successor, which is normally
         * just the next node.  But if cancelled or apparently null,
         * traverse backwards from tail to find the actual
         * non-cancelled successor.
         */
        Node s = node.next;
        if (s == null || s.waitStatus > 0) {//被取消了
            s = null;
            for (Node t = tail; t != null && t != node; t = t.prev)//从尾节点开始找小于等于0的把锁释放
                if (t.waitStatus <= 0)
                    s = t;
        }
        if (s != null)
            LockSupport.unpark(s.thread);
    }


		static final Node EXCLUSIVE = null;

        /** waitStatus value to indicate thread has cancelled */
        static final int CANCELLED =  1;
        /** waitStatus value to indicate successor's thread needs unparking */
        static final int SIGNAL    = -1;
        /** waitStatus value to indicate thread is waiting on condition */
        static final int CONDITION = -2;
        /**
         * waitStatus value to indicate the next acquireShared should
         * unconditionally propagate
         */
        static final int PROPAGATE = -3;

        /**
         * Status field, taking on only the values:
         *   SIGNAL:     The successor of this node is (or will soon be)
         *               blocked (via park), so the current node must
         *               unpark its successor when it releases or
         *               cancels. To avoid races, acquire methods must
         *               first indicate they need a signal,
         *               then retry the atomic acquire, and then,
         *               on failure, block.
         *   CANCELLED:  This node is cancelled due to timeout or interrupt.
         *               Nodes never leave this state. In particular,
         *               a thread with cancelled node never again blocks.
         *   CONDITION:  This node is currently on a condition queue.
         *               It will not be used as a sync queue node
         *               until transferred, at which time the status
         *               will be set to 0. (Use of this value here has
         *               nothing to do with the other uses of the
         *               field, but simplifies mechanics.)
         *   PROPAGATE:  A releaseShared should be propagated to other
         *               nodes. This is set (for head node only) in
         *               doReleaseShared to ensure propagation
         *               continues, even if other operations have
         *               since intervened.
         *   0:          None of the above
         *
         * The values are arranged numerically to simplify use.
         * Non-negative values mean that a node doesn't need to
         * signal. So, most code doesn't need to check for particular
         * values, just for sign.
         *
         * The field is initialized to 0 for normal sync nodes, and
         * CONDITION for condition nodes.  It is modified using CAS
         * (or when possible, unconditional volatile writes).
         */
        volatile int waitStatus;

```





ReentrantReadWriteLock 

```

```

