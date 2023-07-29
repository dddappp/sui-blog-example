module sui_blog_example::blog_scripts {

    use sui::coin;
    use sui::coin::Coin;
    use sui::sui::SUI;
    use sui::transfer;
    use sui::tx_context;

    use sui_blog_example::blog;
    use sui_blog_example::blog_aggregate;

    public fun donate(
        blog: &mut blog::Blog,
        coin: &mut Coin<SUI>,
        amount: u64,
        ctx: &mut tx_context::TxContext,
    ) {
        let a = coin::split(coin, amount, ctx);
        blog_aggregate::donate(blog, coin::into_balance(a), ctx);
    }

    public fun withdraw(
        blog: &mut blog::Blog,
        amount: u64,
        ctx: &mut tx_context::TxContext,
    ) {
        let a = blog_aggregate::withdraw(blog, amount, ctx);
        let c = coin::from_balance(a, ctx);
        transfer::public_transfer(c, tx_context::sender(ctx));
    }
}
