# Sui Client CLI Cheatsheet

[ToC]

## Article aggregate

### Create method

```shell
sui client call --package _PACKAGE_ID_ --module article_aggregate --function create \
--args '"string_title"' '"string_body"' address_owner \
--gas-budget 100000
```

### Update method

```shell
sui client call --package _PACKAGE_ID_ --module article_aggregate --function update \
--args article_Object_ID '"string_title"' '"string_body"' address_owner '["string_tags_item_1","string_tags_item_2"]' '[id_tags_v2_item_1,id_tags_v2_item_2]' \
--gas-budget 100000
```

### Delete method

```shell
sui client call --package _PACKAGE_ID_ --module article_aggregate --function delete \
--args article_Object_ID \
--gas-budget 100000
```

### UpdateComment method

```shell
sui client call --package _PACKAGE_ID_ --module article_aggregate --function update_comment \
--args article_Object_ID \"u64_comment_seq_id\" '"string_commenter"' '"string_body"' address_owner \
--gas-budget 100000
```

### RemoveComment method

```shell
sui client call --package _PACKAGE_ID_ --module article_aggregate --function remove_comment \
--args article_Object_ID \"u64_comment_seq_id\" \
--gas-budget 100000
```

### AddComment method

```shell
sui client call --package _PACKAGE_ID_ --module article_aggregate --function add_comment \
--args article_Object_ID '"string_commenter"' '"string_body"' \
--gas-budget 100000
```

### UpdateTags method

```shell
sui client call --package _PACKAGE_ID_ --module article_aggregate --function update_tags \
--args article_Object_ID \"_TAG_OBJECT_ID_\" \"_TAG_OBJECT_ID_\" \"_TAG_OBJECT_ID_\" \
--gas-budget 100000
```

### UpdateTagsV2 method

```shell
sui client call --package _PACKAGE_ID_ --module article_aggregate --function update_tags_v2 \
--args article_Object_ID \"_TAG_V2_OBJECT_ID_\" \"_TAG_V2_OBJECT_ID_\" \"_TAG_V2_OBJECT_ID_\" \
--gas-budget 100000
```

## Tag aggregate

### Create method

```shell
sui client call --package _PACKAGE_ID_ --module tag_aggregate --function create \
--args '"string_name"' \"_TAG_TAG_NAME_TABLE_OBJECT_ID_\" \
--gas-budget 100000
```

## TagV2 aggregate

### Create method

```shell
sui client call --package _PACKAGE_ID_ --module tag_v2_aggregate --function create \
--args '"string_name"' \
--gas-budget 100000
```

## Blog singleton object

### Update method

```shell
sui client call --package _PACKAGE_ID_ --module blog_aggregate --function update \
--args blog_Object_ID '"string_name"' '[id_articles_item_1,id_articles_item_2]' \
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

