package Design;

/*  355. Design Twitter
    Design a simplified version of Twitter where users can post tweets,
    follow/unfollow another user and is able to see the 10 most recent tweets in the user's news feed.
    Your design should support the following methods:

        1. postTweet(userId, tweetId): Compose a new tweet.
        2. getNewsFeed(userId): Retrieve tge 10 most recent tweet ids in the user's news feed.
           Each item in the news feed must be posted by users who the user followed or by the user herself.
           Tweets must be ordered from most recent to least recent.
        3. follow(followerId, followeeId): Follower follows a followee.
        4. unfollow(followerId, followeeId): Follower unfollows a followee.

    Example:
    Twitter twitter = new Twitter();

    // User 1 posts a new tweet (id = 5).
    twitter.postTweet(1, 5);

    // User 1's news feed should return a list with 1 tweet id -> [5].
    twitter.getNewsFeed(1);

    // User 1 follows user 2.
    twitter.follow(1, 2);

    // User 2 posts a new tweet (id = 6).
    twitter.postTweet(2, 6);

    // User 1's news feed should return a list with 2 tweet ids -> [6, 5].
    // Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
    twitter.getNewsFeed(1);

    // User 1 unfollows user 2.
    twitter.unfollow(1, 2);

    // User 1's news feed should return a list with 1 tweet id -> [5],
    // since user 1 is no longer following user 2.
    twitter.getNewsFeed(1);
 */

import java.util.*;

/*  HashTable + LinkedList + PriorityQueue

 */
public class DesignTwitter {
    // userId 和 tweet（单链表）的对应关系
    private HashMap<Integer, Tweet> twitter;

    // userId 和他关注用户列表的对应关系
    private HashMap<Integer, Set<Integer>> followings;

    // 全局使用的时间戳字段，用户每发布条推文之前 + 1
    private static int timestamp = 0;

    /** 推文类，是一个单链表 */
    private class Tweet {
        // 推文 id
        private int id;
        // 发推文的时间戳
        private int timestamp;
        private Tweet next;

        public Tweet(int id, int timestamp) {
            this.id = id;
            this.timestamp = timestamp;
        }
    }

    /** Initialize your data structure here. */
    public DesignTwitter() {
        followings = new HashMap<>();
        twitter = new HashMap<>();
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        timestamp++;
        if (twitter.containsKey(userId)) {
            Tweet oldHead = twitter.get(userId);
            Tweet newHead = new Tweet(tweetId, timestamp);
            newHead.next = oldHead;
            twitter.put(userId, newHead);
        } else {
            twitter.put(userId, new Tweet(tweetId, timestamp));
        }
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        PriorityQueue<Tweet> maxHeap = new PriorityQueue<>((o1, o2) -> o2.timestamp - o1.timestamp);

        // 如果自己发了推文也要算上
        if (twitter.containsKey(userId)) {
            maxHeap.offer(twitter.get(userId));
        }

        Set<Integer> followingList = followings.get(userId);
        if (followingList != null && followingList.size() > 0) {
            for (Integer followingId : followingList) {
                Tweet tweet = twitter.get(followingId);
                if (tweet != null) {
                    maxHeap.offer(tweet);
                }
            }
        }

        List<Integer> res = new ArrayList<>(10);
        int count = 0;
        while (!maxHeap.isEmpty() && count < 10) {
            Tweet head = maxHeap.poll();
            res.add(head.id);

            if (head.next != null) {
                maxHeap.offer(head.next);
            }
            count++;
        }
        return res;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        // 被关注人不能是自己
        if (followerId == followeeId) return;

        // 获取我自己的关注列表
        Set<Integer> followingList = followings.get(followerId);
        // 没有关注的人，需要初始化关注者列表
        if (followingList == null) {
            Set<Integer> init = new HashSet<>();
            init.add(followeeId);
            followings.put(followerId, init);
        } else {
            if (followingList.contains(followeeId)) return;
            followingList.add(followeeId);
        }
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if (followerId == followeeId) return;

        // 获取我自己的关注列表
        Set<Integer> followingList = followings.get(followerId);

        if (followingList == null) return;

        followingList.remove(followeeId);
    }
}
