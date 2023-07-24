module sui_blog_example::blog_donate_logic {
    use sui::balance::Balance;
    use sui::sui::SUI;
    use sui::tx_context::TxContext;
    use sui_blog_example::blog;

    friend sui_blog_example::blog_aggregate;

    public(friend) fun verify(
        amount: &Balance<SUI>,
        blog: &blog::Blog,
        ctx: &TxContext,
    ): blog::DonationReceived {
        let _ = amount;
        let _ = ctx;
        blog::new_donation_received(
            blog,
        )
    }

    public(friend) fun mutate(
        donation_received: &blog::DonationReceived,
        amount: Balance<SUI>,
        blog: blog::Blog,
        ctx: &TxContext, // modify the reference to mutable if needed
    ): blog::Blog {
        let _ = donation_received;
        let _ = ctx;
        let vault = blog::borrow_mut_vault(&mut blog);
        sui::balance::join(vault, amount);
        blog
    }

}
