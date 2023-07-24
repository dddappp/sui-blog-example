module sui_blog_example::blog_donate_logic {
    use sui::balance;
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
            balance::value(amount),
        )
    }

    public(friend) fun mutate(
        donation_received: &blog::DonationReceived,
        amount: Balance<SUI>,
        blog: &mut blog::Blog,
        ctx: &TxContext, // modify the reference to mutable if needed
    ) {
        //let amount_value = donation_received::amount(donation_received);
        let _ = donation_received;
        let _ = ctx;
        let vault = blog::borrow_mut_vault(blog);
        sui::balance::join(vault, amount);
    }
}
