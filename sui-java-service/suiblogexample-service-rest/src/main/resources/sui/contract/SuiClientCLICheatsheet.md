# Sui Client CLI Cheatsheet

[ToC]

## Article aggregate

### Create method

```shell
sui client call --package _PACKAGE_ID_ --module article_aggregate --function create \
--args \"string_title\" \"string_body\" \
--gas-budget 100000
```

### Update method

```shell
sui client call --package _PACKAGE_ID_ --module article_aggregate --function update \
--args article::article_article \"string_title\" \"string_body\" \
--gas-budget 100000
```

### Delete method

```shell
sui client call --package _PACKAGE_ID_ --module article_aggregate --function delete \
--args article::article_article \
--gas-budget 100000
```

### AddComment method

```shell
sui client call --package _PACKAGE_ID_ --module article_aggregate --function add_comment \
--args article::article_article \"u64_comment_seq_id\" \"string_commenter\" \"string_body\" \
--gas-budget 100000
```

### RemoveComment method

```shell
sui client call --package _PACKAGE_ID_ --module article_aggregate --function remove_comment \
--args article::article_article \"u64_comment_seq_id\" \
--gas-budget 100000
```

### UpdateComment method

```shell
sui client call --package _PACKAGE_ID_ --module article_aggregate --function update_comment \
--args article::article_article \"u64_comment_seq_id\" \"string_commenter\" \"string_body\" \
--gas-budget 100000
```


---

## Tips

You can escape single quotes in string arguments by using the following method when enclosing them within single quotes in a shell:

1. Close the current single quote.
2. Use a backslash `\` to escape the single quote.
3. Open a new set of single quotes to continue the string.

Here is an example of how to escape a single quote within a string enclosed by single quotes in a shell:

```shell
echo 'It'\''s a beautiful day'
```
