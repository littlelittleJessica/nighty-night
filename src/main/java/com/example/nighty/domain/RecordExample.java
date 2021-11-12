package com.example.nighty.domain;

import java.util.ArrayList;
import java.util.List;

public class RecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RecordExample() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Long value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Long value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Long value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Long value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Long value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Long> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Long> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Long value1, Long value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Long value1, Long value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andDateIsNull() {
            addCriterion("`date` is null");
            return (Criteria) this;
        }

        public Criteria andDateIsNotNull() {
            addCriterion("`date` is not null");
            return (Criteria) this;
        }

        public Criteria andDateEqualTo(String value) {
            addCriterion("`date` =", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotEqualTo(String value) {
            addCriterion("`date` <>", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateGreaterThan(String value) {
            addCriterion("`date` >", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateGreaterThanOrEqualTo(String value) {
            addCriterion("`date` >=", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateLessThan(String value) {
            addCriterion("`date` <", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateLessThanOrEqualTo(String value) {
            addCriterion("`date` <=", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateLike(String value) {
            addCriterion("`date` like", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotLike(String value) {
            addCriterion("`date` not like", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateIn(List<String> values) {
            addCriterion("`date` in", values, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotIn(List<String> values) {
            addCriterion("`date` not in", values, "date");
            return (Criteria) this;
        }

        public Criteria andDateBetween(String value1, String value2) {
            addCriterion("`date` between", value1, value2, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotBetween(String value1, String value2) {
            addCriterion("`date` not between", value1, value2, "date");
            return (Criteria) this;
        }

        public Criteria andSleepTimeIsNull() {
            addCriterion("sleep_time is null");
            return (Criteria) this;
        }

        public Criteria andSleepTimeIsNotNull() {
            addCriterion("sleep_time is not null");
            return (Criteria) this;
        }

        public Criteria andSleepTimeEqualTo(String value) {
            addCriterion("sleep_time =", value, "sleepTime");
            return (Criteria) this;
        }

        public Criteria andSleepTimeNotEqualTo(String value) {
            addCriterion("sleep_time <>", value, "sleepTime");
            return (Criteria) this;
        }

        public Criteria andSleepTimeGreaterThan(String value) {
            addCriterion("sleep_time >", value, "sleepTime");
            return (Criteria) this;
        }

        public Criteria andSleepTimeGreaterThanOrEqualTo(String value) {
            addCriterion("sleep_time >=", value, "sleepTime");
            return (Criteria) this;
        }

        public Criteria andSleepTimeLessThan(String value) {
            addCriterion("sleep_time <", value, "sleepTime");
            return (Criteria) this;
        }

        public Criteria andSleepTimeLessThanOrEqualTo(String value) {
            addCriterion("sleep_time <=", value, "sleepTime");
            return (Criteria) this;
        }

        public Criteria andSleepTimeLike(String value) {
            addCriterion("sleep_time like", value, "sleepTime");
            return (Criteria) this;
        }

        public Criteria andSleepTimeNotLike(String value) {
            addCriterion("sleep_time not like", value, "sleepTime");
            return (Criteria) this;
        }

        public Criteria andSleepTimeIn(List<String> values) {
            addCriterion("sleep_time in", values, "sleepTime");
            return (Criteria) this;
        }

        public Criteria andSleepTimeNotIn(List<String> values) {
            addCriterion("sleep_time not in", values, "sleepTime");
            return (Criteria) this;
        }

        public Criteria andSleepTimeBetween(String value1, String value2) {
            addCriterion("sleep_time between", value1, value2, "sleepTime");
            return (Criteria) this;
        }

        public Criteria andSleepTimeNotBetween(String value1, String value2) {
            addCriterion("sleep_time not between", value1, value2, "sleepTime");
            return (Criteria) this;
        }

        public Criteria andWakeupTimeIsNull() {
            addCriterion("wakeup_time is null");
            return (Criteria) this;
        }

        public Criteria andWakeupTimeIsNotNull() {
            addCriterion("wakeup_time is not null");
            return (Criteria) this;
        }

        public Criteria andWakeupTimeEqualTo(String value) {
            addCriterion("wakeup_time =", value, "wakeupTime");
            return (Criteria) this;
        }

        public Criteria andWakeupTimeNotEqualTo(String value) {
            addCriterion("wakeup_time <>", value, "wakeupTime");
            return (Criteria) this;
        }

        public Criteria andWakeupTimeGreaterThan(String value) {
            addCriterion("wakeup_time >", value, "wakeupTime");
            return (Criteria) this;
        }

        public Criteria andWakeupTimeGreaterThanOrEqualTo(String value) {
            addCriterion("wakeup_time >=", value, "wakeupTime");
            return (Criteria) this;
        }

        public Criteria andWakeupTimeLessThan(String value) {
            addCriterion("wakeup_time <", value, "wakeupTime");
            return (Criteria) this;
        }

        public Criteria andWakeupTimeLessThanOrEqualTo(String value) {
            addCriterion("wakeup_time <=", value, "wakeupTime");
            return (Criteria) this;
        }

        public Criteria andWakeupTimeLike(String value) {
            addCriterion("wakeup_time like", value, "wakeupTime");
            return (Criteria) this;
        }

        public Criteria andWakeupTimeNotLike(String value) {
            addCriterion("wakeup_time not like", value, "wakeupTime");
            return (Criteria) this;
        }

        public Criteria andWakeupTimeIn(List<String> values) {
            addCriterion("wakeup_time in", values, "wakeupTime");
            return (Criteria) this;
        }

        public Criteria andWakeupTimeNotIn(List<String> values) {
            addCriterion("wakeup_time not in", values, "wakeupTime");
            return (Criteria) this;
        }

        public Criteria andWakeupTimeBetween(String value1, String value2) {
            addCriterion("wakeup_time between", value1, value2, "wakeupTime");
            return (Criteria) this;
        }

        public Criteria andWakeupTimeNotBetween(String value1, String value2) {
            addCriterion("wakeup_time not between", value1, value2, "wakeupTime");
            return (Criteria) this;
        }

        public Criteria andEmotionIsNull() {
            addCriterion("emotion is null");
            return (Criteria) this;
        }

        public Criteria andEmotionIsNotNull() {
            addCriterion("emotion is not null");
            return (Criteria) this;
        }

        public Criteria andEmotionEqualTo(String value) {
            addCriterion("emotion =", value, "emotion");
            return (Criteria) this;
        }

        public Criteria andEmotionNotEqualTo(String value) {
            addCriterion("emotion <>", value, "emotion");
            return (Criteria) this;
        }

        public Criteria andEmotionGreaterThan(String value) {
            addCriterion("emotion >", value, "emotion");
            return (Criteria) this;
        }

        public Criteria andEmotionGreaterThanOrEqualTo(String value) {
            addCriterion("emotion >=", value, "emotion");
            return (Criteria) this;
        }

        public Criteria andEmotionLessThan(String value) {
            addCriterion("emotion <", value, "emotion");
            return (Criteria) this;
        }

        public Criteria andEmotionLessThanOrEqualTo(String value) {
            addCriterion("emotion <=", value, "emotion");
            return (Criteria) this;
        }

        public Criteria andEmotionLike(String value) {
            addCriterion("emotion like", value, "emotion");
            return (Criteria) this;
        }

        public Criteria andEmotionNotLike(String value) {
            addCriterion("emotion not like", value, "emotion");
            return (Criteria) this;
        }

        public Criteria andEmotionIn(List<String> values) {
            addCriterion("emotion in", values, "emotion");
            return (Criteria) this;
        }

        public Criteria andEmotionNotIn(List<String> values) {
            addCriterion("emotion not in", values, "emotion");
            return (Criteria) this;
        }

        public Criteria andEmotionBetween(String value1, String value2) {
            addCriterion("emotion between", value1, value2, "emotion");
            return (Criteria) this;
        }

        public Criteria andEmotionNotBetween(String value1, String value2) {
            addCriterion("emotion not between", value1, value2, "emotion");
            return (Criteria) this;
        }

        public Criteria andDreamIsNull() {
            addCriterion("dream is null");
            return (Criteria) this;
        }

        public Criteria andDreamIsNotNull() {
            addCriterion("dream is not null");
            return (Criteria) this;
        }

        public Criteria andDreamEqualTo(String value) {
            addCriterion("dream =", value, "dream");
            return (Criteria) this;
        }

        public Criteria andDreamNotEqualTo(String value) {
            addCriterion("dream <>", value, "dream");
            return (Criteria) this;
        }

        public Criteria andDreamGreaterThan(String value) {
            addCriterion("dream >", value, "dream");
            return (Criteria) this;
        }

        public Criteria andDreamGreaterThanOrEqualTo(String value) {
            addCriterion("dream >=", value, "dream");
            return (Criteria) this;
        }

        public Criteria andDreamLessThan(String value) {
            addCriterion("dream <", value, "dream");
            return (Criteria) this;
        }

        public Criteria andDreamLessThanOrEqualTo(String value) {
            addCriterion("dream <=", value, "dream");
            return (Criteria) this;
        }

        public Criteria andDreamLike(String value) {
            addCriterion("dream like", value, "dream");
            return (Criteria) this;
        }

        public Criteria andDreamNotLike(String value) {
            addCriterion("dream not like", value, "dream");
            return (Criteria) this;
        }

        public Criteria andDreamIn(List<String> values) {
            addCriterion("dream in", values, "dream");
            return (Criteria) this;
        }

        public Criteria andDreamNotIn(List<String> values) {
            addCriterion("dream not in", values, "dream");
            return (Criteria) this;
        }

        public Criteria andDreamBetween(String value1, String value2) {
            addCriterion("dream between", value1, value2, "dream");
            return (Criteria) this;
        }

        public Criteria andDreamNotBetween(String value1, String value2) {
            addCriterion("dream not between", value1, value2, "dream");
            return (Criteria) this;
        }

        public Criteria andEvaluationIsNull() {
            addCriterion("evaluation is null");
            return (Criteria) this;
        }

        public Criteria andEvaluationIsNotNull() {
            addCriterion("evaluation is not null");
            return (Criteria) this;
        }

        public Criteria andEvaluationEqualTo(String value) {
            addCriterion("evaluation =", value, "evaluation");
            return (Criteria) this;
        }

        public Criteria andEvaluationNotEqualTo(String value) {
            addCriterion("evaluation <>", value, "evaluation");
            return (Criteria) this;
        }

        public Criteria andEvaluationGreaterThan(String value) {
            addCriterion("evaluation >", value, "evaluation");
            return (Criteria) this;
        }

        public Criteria andEvaluationGreaterThanOrEqualTo(String value) {
            addCriterion("evaluation >=", value, "evaluation");
            return (Criteria) this;
        }

        public Criteria andEvaluationLessThan(String value) {
            addCriterion("evaluation <", value, "evaluation");
            return (Criteria) this;
        }

        public Criteria andEvaluationLessThanOrEqualTo(String value) {
            addCriterion("evaluation <=", value, "evaluation");
            return (Criteria) this;
        }

        public Criteria andEvaluationLike(String value) {
            addCriterion("evaluation like", value, "evaluation");
            return (Criteria) this;
        }

        public Criteria andEvaluationNotLike(String value) {
            addCriterion("evaluation not like", value, "evaluation");
            return (Criteria) this;
        }

        public Criteria andEvaluationIn(List<String> values) {
            addCriterion("evaluation in", values, "evaluation");
            return (Criteria) this;
        }

        public Criteria andEvaluationNotIn(List<String> values) {
            addCriterion("evaluation not in", values, "evaluation");
            return (Criteria) this;
        }

        public Criteria andEvaluationBetween(String value1, String value2) {
            addCriterion("evaluation between", value1, value2, "evaluation");
            return (Criteria) this;
        }

        public Criteria andEvaluationNotBetween(String value1, String value2) {
            addCriterion("evaluation not between", value1, value2, "evaluation");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}